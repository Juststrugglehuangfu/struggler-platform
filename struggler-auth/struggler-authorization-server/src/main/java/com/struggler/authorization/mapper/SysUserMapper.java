package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.common.model.SysUser;
import com.struggler.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名称查询用户信息
     *
     * @param userName 用户名称
     * @return SysUser
     */
    SysUser findByUserName(String userName);

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据条件查询所有用户
     *
     * @param pageParams
     */
    List<SysUser> queryPage(Page<SysUser> pageParams);

    /**
     * @return java.util.List<com.struggler.common.model.SysUser>
     * @Author wolf
     * @Description 根据机构查询用户
     * @Date 12:22 2019/7/13
     * @Param [pageParams]
     * @Param [params]
     **/
    List<SysUser> queryPageByOrg(Page<SysUser> pageParams, @Param("deptId") Long deptId);
}
