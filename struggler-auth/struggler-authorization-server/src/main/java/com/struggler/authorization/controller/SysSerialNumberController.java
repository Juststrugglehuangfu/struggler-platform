package com.struggler.authorization.controller;

import com.struggler.authorization.service.SysSerialNumberService;
import com.struggler.common.entity.Result;
import com.struggler.common.model.SysSerialNumber;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 流水号生成
 *
 * @author wanglp
 * @date 2019/7/11
 **/
@RestController
@RequestMapping("/sys/serialNumber")
public class SysSerialNumberController {

    @Autowired
    private SysSerialNumberService sysSerialNumberService;

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Query<SysSerialNumber> pageParams = new Query<>(params);
        PageUtils userInfoPage = sysSerialNumberService.selectSerialNumberByPage(pageParams);
        return Result.success(userInfoPage);
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysSerialNumber sysSerialNumber) {
        boolean result = sysSerialNumberService.insertOrUpdate(sysSerialNumber);
        if (result) {
            return Result.success("");
        } else {
            return Result.failed("");
        }
    }

    @PostMapping("/delete")
    @Transactional
    public Result delete(@RequestBody Long[] ids) {
        boolean result = sysSerialNumberService.deleteBatchIds(Arrays.asList(ids));
        if (result) {
            return Result.success("");
        } else {
            return Result.failed("");
        }
    }

}
