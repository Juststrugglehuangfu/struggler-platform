package com.struggler.sys;

import com.struggler.common.entity.Result;
import com.struggler.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanglp
 * @date 2019/6/8
 **/
@RestController
@RequestMapping("/test/menu")
public class TestController {


    @Autowired
    SerialNumberProvider serialNumberProvider;

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Result result = serialNumberProvider.getList();
        return result;
    }

    @GetMapping("/lists")
    public Result lists(@RequestParam Map<String, Object> params) {

        return Result.success("dsfdsfds" +
                "fd");
    }

    @GetMapping("/test")
    public String test() {
        return serialNumberProvider.test();
    }

    @GetMapping("/getSerialNumber")
    public Result getSerialNumber() {
        Map<String, Object> params = new HashMap<>();
        params.put("ruleCode", "PDN");
        params.put("prefix", "TEST");
        params.put("dateRegex", DateUtils.DATEFORMAT_YYYYMMDDHH);
        params.put("split", "-");
        return Result.success(serialNumberProvider.getSerialNumber(params));
    }


}
