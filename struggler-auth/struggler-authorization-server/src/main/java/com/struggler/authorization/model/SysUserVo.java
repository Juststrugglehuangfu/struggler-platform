package com.struggler.authorization.model;

import com.struggler.common.model.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: xdqin
 * @date: 2019-07-15 11:03
 * @Version: v1.0
 */
@Data
public class SysUserVo extends SysUser {
    private List<Long> roleIds;
    private Long deptId;
}
