/*
 *
 * FileName: SerialNumberGeneration
 * Author:   Abner(Abner)
 * Date:     2017/1/5 20:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.struggler.authorization.common;

import com.struggler.authorization.mapper.SysSerialNumberMapper;
import com.struggler.common.constant.GlobalConstant;
import com.struggler.common.model.SysSerialNumber;
import com.struggler.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 〈〉<br>
 * 〈流水号码生成器,
 * 使用分布式时将生成器提出来，做一个微服务来提供号码生成。〉
 *
 * @author Abner
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Slf4j
public class SerialNumberGeneration {

    private final SysSerialNumberMapper serialNumberMapper;
    /**
     * 日志记录
     */
    public static final String SYS_SERIAL_NUMBER_DAY = "DAY";
    public static final String SYS_SERIAL_NUMBER_NEVER = "NEVER";
    public static final String SYS_SERIAL_NUMBER_YEAR = "YEAR";
    public static final String SYS_SERIAL_NUMBER_MONTH = "MONTH";
    public static final String SYS_SERIAL_NUMBER_HOUR = "HOUR";
    public static final String SYS_SERIAL_NUMBER_MINUTE = "MINUTE";
    public static final String SYS_SERIAL_NUMBER_SECOND = "SECOND";

    @Autowired
    public SerialNumberGeneration(SysSerialNumberMapper serialNumberMapper) {
        this.serialNumberMapper = serialNumberMapper;
    }


    public String anewUpdateAndGetSerialNum(String dateRegex, String ruleCode, String inSplit, String inPrefix) {
        if (StringUtils.isBlank(ruleCode)) {
            log.error("-##流水号规则编码不能为空。");
            return null;
        }
        //根据流水规则编码更新当前流水
        serialNumberMapper.updateCurrentValueByRuleCode(ruleCode);
        //根据规则编码获取当前流水号及规则信息
        SysSerialNumber serialNumber = serialNumberMapper.selectSerialNumberByRuleCode(ruleCode);
        if (null == serialNumber) {
            log.error("-##根据流水号规则编码：{} 没有找到对应的规则。", ruleCode);
            return null;
        }
        // 流水号重置规则
        String ruleType = serialNumber.getRuleType();
        // 数据库中当前时间
        Date curTime = serialNumber.getCurTime();
        // 流水号长度
        int serialLength = serialNumber.getSerialLength();
        // 当前值
        String currentValue = serialNumber.getCurrentValue();
        // 初始值
        String initValue = serialNumber.getInitValue();
        // 系统当前时间（放在更新操作后面，防止时间判断误差）
        Date systemTime = serialNumber.getSystemTime();
        // 初始值
        String prefix = serialNumber.getPrefix();
        // 初始值
        String split = serialNumber.getSplit();
        // 流水号
        String serialNum;

        // 判断是否需要重置流水号
        if (checkIsReset(ruleType, DateUtils.getDateToString(curTime, DateUtils.DATEFORMAT_YYYYMMDDHHMMSS), DateUtils.getDateToString(systemTime, DateUtils.DATEFORMAT_YYYYMMDDHHMMSS))) {
            serialNum = currentValue;
        } else {
            // 取初始值（重置）
            serialNum = initValue;
            serialNumber.setCurTime(systemTime);
            serialNumber.setCurrentValue(initValue);
            serialNumberMapper.updateById(serialNumber);
        }
        // 根据流水号长度返回组装后的流水号
        if (null != serialNum) {

            String middle = StringUtils.isNotBlank(dateRegex) ? DateUtils.dateTimeFormat(systemTime,
                    DateUtils.DATEFORMAT_YYYYMMDDHHMMSS, dateRegex) : GlobalConstant.STR_EMPTY;
            StringBuilder sb = new StringBuilder();
            if (StringUtils.isNotBlank(inPrefix)) {
                sb.append(inPrefix);
            } else if (StringUtils.isNotBlank(prefix)) {
                sb.append(prefix);
            }
            if (StringUtils.isNotBlank(inSplit)) {
                sb.append(inSplit);
            } else if (StringUtils.isNotBlank(split)) {
                sb.append(split);
            }
            if (StringUtils.isNotBlank(middle)) {
                sb.append(middle);
            }
            if (StringUtils.isNotBlank(inSplit)) {
                sb.append(inSplit);
            } else if (StringUtils.isNotBlank(split)) {
                sb.append(split);
            }
            String after = assembleSerialNum(serialLength, serialNum);
            if (StringUtils.isNotBlank(after)) {
                sb.append(after);
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 功能描述: <br>
     * 〈根据流水号规则判断是否需要重置流水号〉
     *
     * @param ruleType   规则编号
     * @param curTime    数据库中当前时间
     * @param systemTime 系统当前时间
     * @return true：不需要重置，false：需要重置
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static boolean checkIsReset(String ruleType, String curTime, String systemTime) {
        boolean flag;
        switch (ruleType) {
            case SYS_SERIAL_NUMBER_DAY:// 按日重置（使用率最高，放在最前）
                flag = curTime.substring(GlobalConstant.INT0, GlobalConstant.INT8).equals(
                        systemTime.substring(GlobalConstant.INT0, GlobalConstant.INT8));
                break;
            case SYS_SERIAL_NUMBER_NEVER:// 永远都不重置
                flag = true;
                break;
            case SYS_SERIAL_NUMBER_YEAR:// 按年重置
                flag = curTime.substring(GlobalConstant.INT0, GlobalConstant.INT4).equals(
                        systemTime.substring(GlobalConstant.INT0, GlobalConstant.INT4));
                break;
            case SYS_SERIAL_NUMBER_MONTH:// 按月重置
                flag = curTime.substring(GlobalConstant.INT0, GlobalConstant.INT6).equals(
                        systemTime.substring(GlobalConstant.INT0, GlobalConstant.INT6));
                break;
            case SYS_SERIAL_NUMBER_HOUR:// 按小时重置
                flag = curTime.substring(GlobalConstant.INT0, GlobalConstant.INT10).equals(
                        systemTime.substring(GlobalConstant.INT0, GlobalConstant.INT10));
                break;
            case SYS_SERIAL_NUMBER_MINUTE:// 按分重置
                flag = curTime.substring(GlobalConstant.INT0, GlobalConstant.INT12).equals(
                        systemTime.substring(GlobalConstant.INT0, GlobalConstant.INT12));
                break;
            case SYS_SERIAL_NUMBER_SECOND:// 按秒重置
                flag = curTime.equals(systemTime);
                break;
            default:// 永远都不重置
                flag = true;
                break;
        }
        log.debug("checkIsReset ruleType:{},curTime:{},systemTime:{},flag:{}", ruleType, curTime, systemTime, flag);
        return flag;
    }

    /**
     * 功能描述: <br>
     * 〈根据流水号长度拼接流水号〉
     *
     * @param serialLength 流水号设置的长度
     * @param serialNum    获取递增后的流水号
     * @return 拼接后的流水号
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String assembleSerialNum(int serialLength, String serialNum) {
        // 初始化拼接后的流水号
        StringBuilder num = new StringBuilder();
        // 判断设置长度是否大于当前流水号
        if (serialLength > serialNum.length()) {
            // 循环在当前流水号前补0
            for (int i = 0; i < serialLength - serialNum.length(); i++) {
                num.append(GlobalConstant.STR0);
            }
            return num.append(serialNum).toString();
        }
        // 如果长度不大于当前流水号则直接返回当前流水号
        return serialNum;
    }


}
