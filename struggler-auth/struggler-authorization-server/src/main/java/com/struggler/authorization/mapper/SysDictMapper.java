package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.common.model.SysDict;
import com.struggler.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 查询数据字典
     *
     * @param pageParams 参数
     * @return List<SysDict>
     */
    List<SysDict> queryPage(Page<SysDict> pageParams, @Param("params") Query params);

    /**
     * 检查字典编码是否存在
     *
     * @param dicCode 参数
     * @return List<SysDict>
     */
    List<SysDict> findByDicCode(String dicCode);
}
