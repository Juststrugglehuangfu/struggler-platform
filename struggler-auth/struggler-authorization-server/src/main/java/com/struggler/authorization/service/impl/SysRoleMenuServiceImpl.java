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

import java.util.List;
import java.util.Map;


/**
 * 用户管理
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {


    @Override
    public boolean save(SysRoleMenu sysRoleMenu) {
        this.insert(sysRoleMenu);
        return false;
    }

    @Override
    public List<Long> queryRoleMenuIdByRoleId(Long roleId) {
        return baseMapper.queryRoleMenuIdByRoleId(roleId);
    }

    @Override
    public boolean flagDelByRoleId(Long roleId) {
        return baseMapper.flagDelByRoleId(roleId);
    }
    @Override
    public boolean flagDelByRoleIds(List<Long> roleIds) {
        return baseMapper.flagDelByRoleIds(roleIds);
    }
}
