package com.struggler.authorization.controller;

import com.struggler.authorization.model.BaseUserDetail;
import com.struggler.authorization.service.SysMenuService;
import com.struggler.authorization.service.SysUserService;
import com.struggler.common.entity.Result;
import com.struggler.common.enums.GlobalEnum;
import com.struggler.common.model.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author wanglp
 * @date 2019/6/8
 **/
@RestController
@RequestMapping("/sys/menu")
@Slf4j
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 导航菜单
     *
     * @return Result
     */
    @GetMapping("/nav")
    public Result navList(Principal principal) {
        BaseUserDetail userDetail = (BaseUserDetail) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
        Long userId = userDetail.getBaseUser().getId();
        List<SysMenu> menuList = sysMenuService.getUserMenuList(userId);
        //权限信息
        Set<String> permissionList = sysUserService.getPermissionsByUserId(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("menuList", menuList);
        result.put("permissionList", permissionList);
        return Result.success(result);
    }

    @GetMapping("/list")
    public Result list() {
        List<SysMenu> menuList = sysMenuService.selectList(null);
        for (SysMenu sysMenuEntity : menuList) {
            SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return Result.success(menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return Result.success(menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        SysMenu menu = sysMenuService.selectById(id);
        return Result.success(menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.insert(menu);

        return Result.success(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return Result.success(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        if(id <= 31){
            return Result.failed("系统菜单，不能删除");
        }
        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(id);
        if(menuList.size() > 0){
            return Result.failed("请先删除子菜单或按钮");
        }
        sysMenuService.delete(id);
        return Result.success(null);
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new RuntimeException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new RuntimeException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType().equals(GlobalEnum.MenuType.MENU.getValue())){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new RuntimeException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        String parentType = GlobalEnum.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType().equals(GlobalEnum.MenuType.CATALOG.getValue()) ||
                menu.getType().equals(GlobalEnum.MenuType.MENU.getValue())){
            if(!parentType.equals(GlobalEnum.MenuType.CATALOG.getValue())){
                throw new RuntimeException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType().equals(GlobalEnum.MenuType.BUTTON.getValue())){
            if(!parentType.equals(GlobalEnum.MenuType.MENU.getValue())){
                throw new RuntimeException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }

    @GetMapping("/test")
    public String test(){
        return "dsdsdsd";
    }

}
