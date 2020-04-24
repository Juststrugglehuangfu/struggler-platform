package com.struggler.authorization.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysRole;
import com.struggler.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 */
public interface SysRoleService extends IService<SysRole> {


    /**
     * 保存用户
     */
    boolean save(SysRole sysRole);


    /**
     * 分页查询用户信息
     *
     * @param pageParams 分页信息
     * @return PageUtils
     */
    PageUtils selectRoleInfoByPage(Page<SysRole> pageParams, Map<String,Object> queryParams);

    List<Long> queryRoleMenuIdByRoleId(Long roleId);


    boolean updateBySysRole(SysRole sysRole);

    boolean flagDelById(Long id);

    boolean flagDelByIds(List<Long> ids);

    boolean enableById(Long id,Integer status);

    boolean enableByIds(List<Long> ids,Integer status);

    List<SysRole> queryRoleByUserIdAndDeptId(Long userId,Long deptId);
}
