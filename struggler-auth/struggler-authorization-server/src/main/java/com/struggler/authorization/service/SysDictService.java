package com.struggler.authorization.service;

import com.baomidou.mybatisplus.service.IService;
import com.struggler.common.model.SysDict;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 数据字典服务类
 *
 * @author wanglp
 * @date 2019/7/5
 **/
public interface SysDictService extends IService<SysDict> {

    /**
     * 查询所有数据字典
     *
     * @param pageParams 参数
     * @return PageUtils
     */
    PageUtils selectSysDictByPage(Query<SysDict> pageParams);

    /**
     * 检查字典编码是否存在
     *
     * @param dicCode 参数编码
     * @return List
     */
    List<SysDict> findByDicCodes(String dicCode);
    /**
     * 保存
     *
     * @param dict 参数
     * @return List
     */
    void add(SysDict dict);

}
