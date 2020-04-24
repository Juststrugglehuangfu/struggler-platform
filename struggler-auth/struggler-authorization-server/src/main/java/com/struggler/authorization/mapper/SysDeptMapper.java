package com.struggler.authorization.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.common.model.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 根据条件查询所有用户
     *
     * @param pageParams
     */
    List<SysDept> queryPage(Page<SysDept> pageParams);

    /**
     * 查詢子部門
     * @param parentId
     * @return
     */
    List<SysDept> queryListParentId(Long parentId);

    List<SysDept> queryDeptByInfo(SysDept sysDept);

}
