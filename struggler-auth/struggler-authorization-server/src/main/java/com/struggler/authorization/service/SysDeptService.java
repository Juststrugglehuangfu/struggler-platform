package com.struggler.authorization.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.struggler.authorization.model.SysDeptVo;
import com.struggler.common.model.SysDept;
import com.struggler.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @类名： SysOrgService
 * @描述：【组织机构】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/13 11:27
 * @修改人：wolf
 * @修改时间：2019/7/13 11:27
 **/
public interface SysDeptService extends IService<SysDept> {
    /**
     * 查询所有组织机构带分页
     *
     * @param pageParams 参数
     * @return PageUtils
     */
    PageUtils selectSysDeptByPage(Page<SysDept> pageParams);

    /**
     * @return java.util.List<com.struggler.common.model.SysOrg>
     * @Author wolf
     * @Description 查询组织机构不分页
     * @Date 11:29 2019/7/13
     * @Param [params]
     **/
    List<SysDeptVo> selectSysDept(Map<String, Object> params);

    /**
     * 查詢子部門
     * @param parentId
     * @return
     */
    List<SysDept> queryListParentId(Long parentId);

    List<SysDept> queryDeptByInfo(SysDept sysDept);
}
