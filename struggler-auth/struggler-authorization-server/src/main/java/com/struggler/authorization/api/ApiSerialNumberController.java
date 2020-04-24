package com.struggler.authorization.api;

import com.struggler.authorization.common.SerialNumberGeneration;
import com.struggler.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wanglp
 * @date 2019/7/12
 **/
@RestController
@RequestMapping("/api/serialNumber")
@Slf4j
public class ApiSerialNumberController {

    @Autowired
    private SerialNumberGeneration serialNumberGeneration;

    /**
     * 获取流水号
     *
     * @param ruleCode  规则编码
     * @param prefix    前缀
     * @param dateRegex 日期格式化 默认 yyyyMMddHHmmss
     * @param split     分割符 如果为空，默认空
     * @return 流水号
     */
    @PostMapping("/anewUpdateAndGetSerialNum")
    public Result anewUpdateAndGetSerialNum(@RequestBody Map<String, Object> params) {
        String ruleCode = MapUtils.getString(params, "ruleCode");
        String prefix = MapUtils.getString(params, "prefix");
        String dateRegex = MapUtils.getString(params, "dateRegex");
        String split = MapUtils.getString(params, "split");
        String serialNumber = serialNumberGeneration.anewUpdateAndGetSerialNum(dateRegex, ruleCode, split, prefix);
        if (StringUtils.isBlank(serialNumber)) {
            log.error("checkIsReset ruleCode:{},prefix:{},dateRegex:{},split:{},return null", ruleCode, prefix, dateRegex, split);
            return Result.failed("无法获取序列号信息，请检查参数信息");
        }
        return Result.success(serialNumber);
    }
}
