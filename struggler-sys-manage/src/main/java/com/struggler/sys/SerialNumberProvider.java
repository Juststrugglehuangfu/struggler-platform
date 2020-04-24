package com.struggler.sys;

import com.struggler.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 流水号生成服务服务端连接工具
 *
 * @author wanglp
 * @date 2019/7/12
 **/
@Service
@FeignClient(value = "struggler-authorization-server", fallback = SerialNumberProviderHystrix.class)
public interface SerialNumberProvider {

    @RequestMapping(value = "/api/serialNumber/anewUpdateAndGetSerialNum", method = RequestMethod.POST)
    Result getSerialNumber(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/sys/menu/list", method = RequestMethod.GET)
    Result getList();

    @RequestMapping(value = "/sys/menu/test", method = RequestMethod.GET, consumes = "application/json")
    String test();
}
