package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.netflix.discovery.converters.Auto;
import com.struggler.authorization.mapper.SysUserMapper;
import com.struggler.authorization.model.SysUserVo;
import com.struggler.authorization.service.SysMenuService;
import com.struggler.authorization.service.SysUserDeptService;
import com.struggler.authorization.service.SysUserRoleService;
import com.struggler.authorization.service.SysUserService;
import com.struggler.common.constant.GlobalConstant;
import com.struggler.common.model.SysMenu;
import com.struggler.common.model.SysUser;
import com.struggler.common.model.SysUserDept;
import com.struggler.common.model.SysUserRole;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户管理
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserDeptService sysUserDeptService;

    @Override
    public List<String> queryAllPerms(Long userId)
    {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId)
    {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUser queryByUserName(String username)
    {
        return baseMapper.findByUserName(username);
    }

    @Override
    public Set<String> getPermissionsByUserId(Long userId)
    {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == GlobalConstant.SUPER_ADMIN)
        {
            List<SysMenu> menuList = sysMenuService.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenu menu : menuList)
            {
                permsList.add(menu.getPermissions());
            }
        }
        else
        {
            permsList = queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList)
        {
            if (StringUtils.isBlank(perms))
            {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;

    }

    @Override
    public PageUtils selectUserInfoByPage(Page<SysUser> pageParams)
    {
        List<SysUser> sysUserList = baseMapper.queryPage(pageParams);
        pageParams.setRecords(sysUserList);
        return new PageUtils(pageParams);
    }

    @Override
    public PageUtils selectUserByDeptByPage(Page<SysUser> pageParams, Long deptId)
    {
        List<SysUser> sysUserList = baseMapper.queryPageByOrg(pageParams, deptId);
        pageParams.setRecords(sysUserList);
        return new PageUtils(pageParams);

    }

    /**
     * @param id
     * @return com.struggler.common.model.SysUser
     * @Author wolf
     * @Description 根据ID查询用户
     * @Date 22:22 2019/7/14
     * @Param [id]
     */
    @Override
    public SysUser selectById(Long id)
    {
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional
    public boolean save(SysUserVo user)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean res = sysUserService.insert(user);
        if (res)
        {
            List<Long> userRoleIds = user.getRoleIds();
            res =  insertUserRoles(userRoleIds, user.getId());
            if (res){
                SysUserDept sysUserDept = new SysUserDept();
                sysUserDept.setDeptId(user.getDeptId());
                sysUserDept.setUserId(user.getId());
                return sysUserDeptService.insert(sysUserDept);
            }
        }
        return false;

        //		//检查角色是否越权
        //		checkRole(user);
    }

    private boolean insertUserRoles(List<Long> userRoleIds, Long userId)
    {
        //删除原角色
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        sysUserRoleService.deleteByMap(map);
        //新增角色信息
        List<SysUserRole> userRoleList = new ArrayList<>();
        for (Long roleId : userRoleIds)
        {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            sysUserRole.setComSerial("");
            sysUserRole.setComSerialId(1l);
            userRoleList.add(sysUserRole);
        }
        if (userRoleList.size() > 0)
        {
            return sysUserRoleService.insertBatch(userRoleList);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(SysUserVo user)
    {
        boolean res = this.updateById(user);
        if (res) {
            return insertUserRoles(user.getRoleIds(), user.getId());
        }
        return res;
        //
        //		//检查角色是否越权
        //		checkRole(user);
    }

    @Override
    public boolean deleteBatch(Long[] userId) {
        		boolean res = this.deleteBatchIds(Arrays.asList(userId));
        		if (res){
        		    //删除用户部门关系
                    res = sysUserDeptService.deleteBatchByUserId(Arrays.asList(userId));
                    //删除用户角色关系
                    if (res){
                        res = sysUserRoleService.deleteBatchByUserIds(Arrays.asList(userId));
                        return res;
                    }
                }
        		return false;
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword)
    {
        //		SysUser userEntity = new SysUser();
        //		userEntity.setPassword(newPassword);
        //		return this.update(userEntity,
        //				new EntityWrapper<SysUser>().eq("user_id", userId).eq("password", password));
        return true;
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUser user)
    {
        //		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
        //			return;
        //		}
        //		//如果不是超级管理员，则需要判断用户的角色是否自己创建
        //		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
        //			return ;
        //		}
        //
        //		//查询用户创建的角色列表
        //		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
        //
        //		//判断是否越权
        //		if(!roleIdList.containsAll(user.getRoleIdList())){
        //			throw new RRException("新增用户所选角色，不是本人创建");
        //		}
    }
}
