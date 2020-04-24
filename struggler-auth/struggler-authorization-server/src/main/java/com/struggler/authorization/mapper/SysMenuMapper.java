package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.struggler.common.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户ID查询当前用户所拥有的菜单权限
     *
     * @param userId 用户名
     * @return 权限信息
     */
    List<String> findUserPermissionsByUserId(Long userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

}
