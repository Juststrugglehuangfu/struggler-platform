package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.struggler.common.model.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {


    List<Long> queryRoleMenuIdByRoleId(@Param("roleId") Long roleId);

    boolean flagDelByRoleId(@Param("roleId") Long roleId);

    boolean flagDelByRoleIds(@Param("roleIds") List<Long> roleIds);

}
