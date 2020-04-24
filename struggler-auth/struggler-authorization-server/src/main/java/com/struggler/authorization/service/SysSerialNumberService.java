package com.struggler.authorization.service;

import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysSerialNumber;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;

/**
 * 自动生成序列号接口
 *
 * @author wanglp
 * @date 2019/7/11
 **/
public interface SysSerialNumberService extends IService<SysSerialNumber> {

    PageUtils  selectSerialNumberByPage(Query<SysSerialNumber> pageParams);

}
