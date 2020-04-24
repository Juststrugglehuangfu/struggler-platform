package com.struggler.authorization.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.authorization.model.SysUserVo;
import com.struggler.authorization.service.SysRoleService;
import com.struggler.authorization.service.SysUserRoleService;
import com.struggler.authorization.service.SysUserService;
import com.struggler.common.entity.Result;
import com.struggler.common.model.SysRole;
import com.struggler.common.model.SysUser;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author wanglp
 * @date 2019/6/28
 **/
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @Author wolf
     * @Description 查询列表
     * @Date 22:24 2019/7/14
     * @Param [params]
     * @return com.struggler.common.entity.Result
     **/
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Page<SysUser> pageParams = new Query<SysUser>(params).getPage();
        PageUtils userInfoPage = sysUserService.selectUserInfoByPage(pageParams);
        return Result.success(userInfoPage);
    }

    /**
     * @Author wolf
     * @Description 新增用户
     * @Date 22:39 2019/7/14
     * @Param [sysUser]
     * @return com.struggler.common.entity.Result
     **/
    @PostMapping("/save")
    public Result save(@RequestBody SysUserVo sysUser){
        return sysUserService.save(sysUser)?Result.success("新增成功"):Result.failed("新增失败");
    }



    /**
     * @Author wolf
     * @Description 更新用户
     * @Date 22:39 2019/7/14
     * @Param [sysUser]
     * @return com.struggler.common.entity.Result
     **/
    @PostMapping("/update")
    public Result update(@RequestBody SysUserVo sysUser){
        return sysUserService.update(sysUser)?Result.success("更新成功"):Result.failed("更新失败");
    }


    /**
     * @Author wolf
     * @Description 根据部门查询用户
     * @Date 22:24 2019/7/14
     * @Param [params]
     * @return com.struggler.common.entity.Result
     **/
  @GetMapping("/listByDept")
    public Result listByOrg(@RequestParam Map<String, Object> params) {
        Page<SysUser> pageParams = new Query<SysUser>(params).getPage();
        if (params.get("deptId") == null) {
            return Result.failed("deptId is null");
        }
        PageUtils userInfoPage = sysUserService.selectUserByDeptByPage(pageParams, Long.parseLong(params.get("deptId").toString()));
        return Result.success(userInfoPage);
    }

    @GetMapping("/listRoleByUser")
    public Result listRoleByUser(@RequestParam Map<String,String> params){
        List<SysRole> userRoleList = sysRoleService.queryRoleByUserIdAndDeptId(Long.parseLong(params.get("userId")),Long.parseLong(params.get("deptId")));
        Map<String, Object> map = new HashMap<>();
        map.put("status",1);
        List<SysRole> roleList = sysRoleService.selectByMap(map);
        Map<String, List<SysRole>> data = new HashMap<>();
        data.put("userRoleList", userRoleList);
        data.put("roleList", roleList);
        return Result.success(data);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable Long id){
        return Result.success(sysUserService.selectById(id));
    }
}
