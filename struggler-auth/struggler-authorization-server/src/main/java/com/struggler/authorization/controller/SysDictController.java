package com.struggler.authorization.controller;

import com.struggler.authorization.service.SysDictService;
import com.struggler.common.entity.Result;
import com.struggler.common.model.SysDict;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author wanglp
 * @date 2019/7/5
 **/
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 数据字典列表查询
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Query<SysDict> pageParams = new Query<>(params);
        PageUtils userInfoPage = sysDictService.selectSysDictByPage(pageParams);
        return Result.success(userInfoPage);
    }

    /**
     * 数据字典列表保存
     *
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysDict dict) {
       sysDictService.add(dict);
        return null;
    }

    /**
     * 检查字典编码是否存在
     *
     * @return
     */
    @PostMapping("/checkDtype")
    public Result checkDtype(@RequestBody SysDict entity) {
        String dicCode =entity.getDicCode();
        List<SysDict> result =sysDictService.findByDicCodes(dicCode);
        return Result.success(result);
    }

}
