package com.struggler.authorization.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.authorization.service.SysRoleService;
import com.struggler.authorization.service.SysUserService;
import com.struggler.common.entity.Result;
import com.struggler.common.model.SysMenu;
import com.struggler.common.model.SysRole;
import com.struggler.common.model.SysUser;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户管理
 *
 * @author wanglp
 * @date 2019/6/28
 **/
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/list")
    public Result list(@RequestBody Map<String, Object> params) {
        Page<SysRole> pageParams = new Query<SysRole>(params).getPage();
        params.put("comSerial","999999");
        params.put("del",1);
        PageUtils roleInfoByPage = sysRoleService.selectRoleInfoByPage(pageParams,params);
        return Result.success(roleInfoByPage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysRole sysRole){
        sysRole.setComSerial("999999");
        sysRole.setComSerialId(999999L);
        sysRole.setDel(1);
        sysRole.setRevision(0);
        sysRole.setCreatedDate(new Date());
        sysRole.setCreatedBy(999999L);
        sysRole.setUpdatedDate(new Date());
        sysRole.setUpdatedBy(999999L);
        sysRoleService.save(sysRole);
        return Result.success(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysRole sysRole){
        sysRole.setUpdatedBy(999999L);
        sysRoleService.updateBySysRole(sysRole);
        return Result.success(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids){
        if(ids.length<=1){
            sysRoleService.flagDelById(ids[0]);
        }else{
            List<Long> idList = new ArrayList<>(ids.length);
            Collections.addAll(idList,ids);
            sysRoleService.flagDelByIds(idList);
        }
        return Result.success(null);
    }

    /**
     * 启用、禁用
     */
    @PostMapping("/enable")
    public Result enable(@RequestBody Map<String,Object> params){
        List<Long> idLists = new ArrayList<>();
        List<Object> paramIds = ((List)params.get("ids"));
        if(null!=paramIds && paramIds.size()>0){
            paramIds.forEach(obj -> {idLists.add(Long.parseLong(obj.toString()));});
        }
        Integer status = (Integer) params.get("status");
        Long[] ids=idLists.toArray(new Long[idLists.size()]);
        if(ids.length<=1){
            sysRoleService.enableById(ids[0],status);
        }else{
            List<Long> idList = new ArrayList<>(ids.length);
            Collections.addAll(idList,ids);
            sysRoleService.enableByIds(idList,status);
        }
        return Result.success(null);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        SysRole sysRole = sysRoleService.selectById(id);
            sysRole.setMenuIdList(sysRoleService.queryRoleMenuIdByRoleId(id));
        return Result.success(sysRole);
    }

}
