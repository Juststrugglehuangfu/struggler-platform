package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysRoleMapper;
import com.struggler.authorization.mapper.SysRoleMenuMapper;
import com.struggler.authorization.service.SysRoleMenuService;
import com.struggler.authorization.service.SysRoleService;
import com.struggler.common.model.SysRole;
import com.struggler.common.model.SysRoleMenu;
import com.struggler.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 用户管理
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public PageUtils selectRoleInfoByPage(Page<SysRole> pageParams,Map<String,Object> queryParams) {
        List<SysRole> sysRoleList = baseMapper.queryPage(queryParams);
        pageParams.setRecords(sysRoleList);
        return new PageUtils(pageParams);
    }

    @Override
    public List<Long> queryRoleMenuIdByRoleId(Long roleId) {
        return sysRoleMenuService.queryRoleMenuIdByRoleId(roleId);
    }

    @Override
    public boolean updateBySysRole(SysRole sysRole) {
        this.updateById(sysRole);
        sysRoleMenuService.flagDelByRoleId(sysRole.getId());
        if(null!=sysRole.getMenuIdList()){
            for(Long menuId:sysRole.getMenuIdList()){
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRole.getId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setCreatedBy(sysRole.getCreatedBy());
                sysRoleMenu.setCreatedDate(sysRole.getCreatedDate());
                sysRoleMenu.setUpdatedBy(sysRole.getUpdatedBy());
                sysRoleMenu.setUpdatedDate(sysRole.getUpdatedDate());
                sysRoleMenu.setDel(1);
                sysRoleMenu.setRevision(1);
                sysRoleMenuService.save(sysRoleMenu);
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean flagDelById(Long id) {
        sysRoleMenuService.flagDelByRoleId(id);
        return baseMapper.flagDelById(id);
    }

    @Override
    @Transactional
    public boolean flagDelByIds(List<Long> ids) {
        sysRoleMenuService.flagDelByRoleIds(ids);
        return baseMapper.flagDelByIds(ids);
    }

    @Override
    public boolean enableById(Long id, Integer status) {
        return baseMapper.enableById(id,status);
    }

    @Override
    public boolean enableByIds(List<Long> ids, Integer status) {
        return baseMapper.enableByIds(ids,status);
    }

    @Override
    public List<SysRole> queryRoleByUserIdAndDeptId(Long userId, Long deptId) {
        List<SysRole> userRoleList = baseMapper.queryRoleByUserId(userId);
        List<SysRole> deptRoleList = baseMapper.queryRoleByDeptId(deptId);
        userRoleList.removeAll(deptRoleList);
        userRoleList.addAll(deptRoleList);
        return userRoleList;
    }

    @Override
    @Transactional
    public boolean save(SysRole sysRole) {
		this.insert(sysRole);
		if(null!=sysRole.getMenuIdList()){
		    for(Long menuId:sysRole.getMenuIdList()){
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRole.getId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setCreatedBy(sysRole.getCreatedBy());
                sysRoleMenu.setCreatedDate(sysRole.getCreatedDate());
                sysRoleMenu.setUpdatedBy(sysRole.getUpdatedBy());
                sysRoleMenu.setUpdatedDate(sysRole.getUpdatedDate());
                sysRoleMenu.setDel(1);
                sysRoleMenu.setRevision(1);
		        sysRoleMenuService.save(sysRoleMenu);
            }
        }
        return true;
    }

}
