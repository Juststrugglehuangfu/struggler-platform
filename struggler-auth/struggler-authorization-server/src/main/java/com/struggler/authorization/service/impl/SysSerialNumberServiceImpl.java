package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysSerialNumberMapper;
import com.struggler.authorization.service.SysSerialNumberService;
import com.struggler.common.model.SysSerialNumber;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自动生成序列号实现类
 *
 * @author wanglp
 * @date 2019/7/11
 **/
@Service
@Slf4j
public class SysSerialNumberServiceImpl extends ServiceImpl<SysSerialNumberMapper, SysSerialNumber> implements SysSerialNumberService {

    @Override
    public PageUtils selectSerialNumberByPage(Query<SysSerialNumber> params) {
        Page<SysSerialNumber> pageParams = params.getPage();
        List<SysSerialNumber> sysSerialNumberList = this.baseMapper.queryPage(pageParams, params);
        pageParams.setRecords(sysSerialNumberList);
        return new PageUtils(pageParams);
    }


}
