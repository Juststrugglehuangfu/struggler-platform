package com.struggler.authorization.service;

import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysUserRole;

import java.util.List;

/**
 * @Description:
 * @Author: xdqin
 * @date: 2019-07-15 11:29
 * @Version: v1.0
 */
public interface SysUserRoleService   extends IService<SysUserRole> {

    /**
      * @Author:xdqin
      * @Description: 根据用户ID列表删除role
      * @date  2019-07-15 17:18

      * @param userIds
      * @return boolean
      */
    boolean deleteBatchByUserIds(List<Long> userIds);
}
