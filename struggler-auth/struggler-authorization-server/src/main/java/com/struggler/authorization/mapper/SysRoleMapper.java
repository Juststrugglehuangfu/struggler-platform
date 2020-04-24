package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.common.model.SysRole;
import com.struggler.common.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据条件查询所有用户
     *
     * @param params
     */
    List<SysRole> queryPage(@Param("params") Map<String,Object> params);

    boolean flagDelById(@Param("id") Long id);

    boolean flagDelByIds(@Param("ids") List<Long> ids);

    boolean enableById(@Param("id") Long id,@Param("status") Integer status);

    boolean enableByIds(@Param("ids") List<Long> ids,@Param("status") Integer status);

    List<SysRole> queryRoleByUserId(@Param("userId")Long userId);

    List<SysRole> queryRoleByDeptId(@Param("deptId")Long deptId);
}
