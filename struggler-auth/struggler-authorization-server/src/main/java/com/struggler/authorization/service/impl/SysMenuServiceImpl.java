package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysMenuMapper;
import com.struggler.authorization.service.SysMenuService;
import com.struggler.authorization.service.SysUserService;
import com.struggler.common.constant.GlobalConstant;
import com.struggler.common.enums.GlobalEnum;
import com.struggler.common.model.SysMenu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单实现类
 *
 * @author wanglp
 * @date 2019/6/8
 **/
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == GlobalConstant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for (SysMenu entity : menuList) {
            //目录
            if (entity.getType().equals(GlobalEnum.MenuType.CATALOG.getValue())
                    || entity.getType().equals(GlobalEnum.MenuType.MENU.getValue())) {
                entity.setChildren(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
            }
            if (!entity.getType().equals(GlobalEnum.MenuType.BUTTON.getValue())
                    || (entity.getType().equals(GlobalEnum.MenuType.BUTTON.getValue()) && StringUtils.isNotBlank(entity.getUrl()))) {
                subMenuList.add(entity);
            }
        }

        return subMenuList;
    }

    @Override
    public void delete(Long menuId) {

    }


}
