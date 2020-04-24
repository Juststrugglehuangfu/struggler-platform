package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysUserRoleMapper;
import com.struggler.authorization.service.SysUserRoleService;
import com.struggler.common.model.SysUserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xdqin
 * @date: 2019-07-15 11:32
 * @Version: v1.0
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRole> implements SysUserRoleService {

    @Override
    public boolean deleteBatchByUserIds(List<Long> userIds) {
        return baseMapper.deleteBatchByUserIds(userIds);
    }
}
