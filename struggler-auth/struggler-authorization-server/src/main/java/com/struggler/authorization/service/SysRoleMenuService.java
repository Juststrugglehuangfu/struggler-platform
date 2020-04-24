package com.struggler.authorization.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysRole;
import com.struggler.common.model.SysRoleMenu;
import com.struggler.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {


    /**
     * 保存用户
     */
    boolean save(SysRoleMenu sysRoleMenu);

    /**
     * @author : scorpio
     * @description 根据角色查询角色菜单权限
     * @create :  2019-07-14
     **/

    List<Long> queryRoleMenuIdByRoleId(Long roleId);


    boolean flagDelByRoleId(Long roleId);

    boolean flagDelByRoleIds(List<Long> roleIds);

}
