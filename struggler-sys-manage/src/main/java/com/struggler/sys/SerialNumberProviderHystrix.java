package com.struggler.sys;

import com.struggler.common.entity.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author wanglp
 * @date 2019/7/12
 **/
@Slf4j
public class SerialNumberProviderHystrix implements SerialNumberProvider {

    @Override
    public Result getSerialNumber(Map<String, Object> params) {
        log.error("getSerialNumber 调用流水号生成失败");
        return null;
    }

    @Override
    public Result getList() {
        return Result.failed("shibai");
    }

    @Override
    public String test() {
        return "fuck";
    }
}
