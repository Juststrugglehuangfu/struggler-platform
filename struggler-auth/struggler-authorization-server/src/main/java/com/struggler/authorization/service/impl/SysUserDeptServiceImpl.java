/* ====================================================================
 Copyright (C) 2019, All rights reserved.
 Company ISS. 
 Description:com.struggler.authorization.service.impl
 FileName: SysUserDeptServiceImpl.java
 Author: xdqin
 Date: 2019-07-15 16:32
 Version: v1.0
 ==================================================================== */
package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysUserDeptMapper;
import com.struggler.authorization.service.SysUserDeptService;
import com.struggler.common.model.SysUserDept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: xdqin
 * @date: 2019-07-15 16:32
 * @Version: v1.0
 */
@Service("sysUserDeptService")
public class SysUserDeptServiceImpl extends ServiceImpl<SysUserDeptMapper, SysUserDept> implements SysUserDeptService {


    @Override
    public boolean deleteBatchByUserId(List<Long> userIds) {
        return baseMapper.deleteBatchByUserIds(userIds);
    }
}
