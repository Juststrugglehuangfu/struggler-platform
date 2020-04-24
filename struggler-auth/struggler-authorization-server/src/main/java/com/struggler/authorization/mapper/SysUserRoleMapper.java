package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.struggler.common.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * @Author:xdqin
     * @Description: 根据用户ID批量删除
     * @date 2019-07-15 16:55

     * @param userIds
     * @return java.lang.Integer
     */
    boolean deleteBatchByUserIds(@Param("userIds") List<Long> userIds);
}
