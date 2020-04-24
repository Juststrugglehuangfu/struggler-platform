package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysDeptMapper;
import com.struggler.authorization.model.SysDeptVo;
import com.struggler.authorization.service.SysDeptService;
import com.struggler.common.model.SysDept;
import com.struggler.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @类名： SysOrgServiceImpl
 * @描述：【】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/13 11:40
 * @修改人：wolf
 * @修改时间：2019/7/13 11:40
 **/
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    /**
     * 查询所有组织机构带分页
     *
     * @param pageParams 参数
     * @return PageUtils
     */
    @Override
    public PageUtils selectSysDeptByPage(Page<SysDept> pageParams) {
        List<SysDept> sysDeptList = baseMapper.queryPage(pageParams);
        pageParams.setRecords(sysDeptList);
        return new PageUtils(pageParams);
    }

    /**
     * @return java.util.List<com.struggler.common.model.SysOrg>
     * @Author wolf
     * @Description 查询组织机构不分页
     * @Date 11:29 2019/7/13
     * @Param [params]
     **/
    @Override
    public List<SysDeptVo> selectSysDept(Map<String, Object> params) {
        List<SysDept> list = baseMapper.selectByMap(params);
        List<SysDeptVo> rootList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            //获取根节点
            Iterator<SysDept> iterator = list.iterator();
            while (iterator.hasNext()) {
                SysDept sysDept = iterator.next();
                if (sysDept.getParentId() == null) {
                    SysDeptVo sysDeptVo = new SysDeptVo();
                    BeanUtils.copyProperties(sysDept, sysDeptVo);
                    sysDeptVo.setChildren(new ArrayList<>());
                    rootList.add(sysDeptVo);
                    iterator.remove();
                }
            }
            if (!rootList.isEmpty()) {
                for (SysDeptVo sysDeptVo : rootList) {
                    children(sysDeptVo, list);
                }
            }
        }
        return rootList;
    }

    @Override
    public List<SysDept> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysDept> queryDeptByInfo(SysDept sysDept) {
        return baseMapper.queryDeptByInfo(sysDept);
    }

    /**
     * @return com.struggler.authorization.model.SysDeptVo
     * @Author wolf
     * @Description 递归组装字节点
     * @Date 15:45 2019/7/13
     * @Param [sysDeptVo, source]
     **/
    private SysDeptVo children(SysDeptVo sysDeptVo, List<SysDept> source) {
        Iterator<SysDept> iterator = source.iterator();
        while (iterator.hasNext()) {
            SysDept sysDept = iterator.next();
            if (sysDept.getParentId() == sysDeptVo.getId()) {
                SysDeptVo child = new SysDeptVo();
                BeanUtils.copyProperties(sysDept, child);
                child.setChildren(new ArrayList<>());
                sysDeptVo.getChildren().add(child);
                iterator.remove();
                children(child, source);
            }
        }
        return sysDeptVo;
    }
}
