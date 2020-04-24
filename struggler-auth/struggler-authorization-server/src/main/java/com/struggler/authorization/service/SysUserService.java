package com.struggler.authorization.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.struggler.authorization.model.SysUserVo;
import com.struggler.common.model.SysUser;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 系统用户
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 保存用户
     */
    boolean save(SysUserVo user);

    /**
     * 修改用户
     */
    boolean update(SysUserVo user);

    /**
     * 删除用户
     */
    boolean deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);

    /**
     * 根据当前用户获取权限信息
     *
     * @param userId 用户id
     * @return 权限信息
     */
    Set<String> getPermissionsByUserId(Long userId);

    /**
     * 分页查询用户信息
     *
     * @param pageParams 分页信息
     * @return PageUtils
     */
    PageUtils selectUserInfoByPage(Page<SysUser> pageParams);

    /**
     * @return com.struggler.common.utils.PageUtils
     * @Author wolf
     * @Description 根据部门查询用户
     * @Date 12:44 2019/7/13
     * @Param [pageParams]
     * @Param [deptId]
     **/
    PageUtils selectUserByDeptByPage(Page<SysUser> pageParams, Long deptId);

    /**
     * @return com.struggler.common.model.SysUser
     * @Author wolf
     * @Description 根据ID查询用户
     * @Date 22:22 2019/7/14
     * @Param [id]
     **/
    SysUser selectById(Long id);
}
