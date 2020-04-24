package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.common.model.SysSerialNumber;
import com.struggler.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysSerialNumberMapper extends BaseMapper<SysSerialNumber> {
    /**
     * 分页查询
     *
     * @param pageParams 分页参数
     * @param params     查询参数
     * @return List<SysSerialNumber>
     */
    List<SysSerialNumber> queryPage(Page<SysSerialNumber> pageParams, @Param("params")Query<SysSerialNumber> params);

    /**
     * 根据ruleCode更新当前值
     * @param ruleCode 规则编号
     * @return Integer
     */
    Integer updateCurrentValueByRuleCode(@Param("ruleCode")String ruleCode);

    /**
     * 根据规则编码获取当前流水号及规则信息
     * @param ruleCode 规则编号
     * @return Map
     */
    SysSerialNumber selectSerialNumberByRuleCode(@Param("ruleCode")String ruleCode);
}
