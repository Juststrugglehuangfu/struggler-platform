/* ====================================================================
 Copyright (C) 2019, All rights reserved.
 Company ISS. 
 Description:com.struggler.authorization.service
 FileName: SysUserDeptService.java
 Author: xdqin
 Date: 2019-07-15 16:22
 Version: v1.0
 ==================================================================== */
package com.struggler.authorization.service;

import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysUserDept;

import java.util.List;

/**
 * @Description:
 * @Author: xdqin
 * @date: 2019-07-15 16:22
 * @Version: v1.0
 */
public interface SysUserDeptService extends IService<SysUserDept> {

    /** 
      * @Author:xdqin
      * @Description: 根据用户ID批量删除
      * @date  2019-07-15 16:55
            
      * @param userIds
      * @return boolean
      */
    boolean deleteBatchByUserId(List<Long> userIds);

}
