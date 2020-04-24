package com.struggler.authorization.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.struggler.authorization.service.SysDeptService;
import com.struggler.common.entity.Result;
import com.struggler.common.model.SysDept;
import com.struggler.common.model.SysMenu;
import com.struggler.common.model.SysRole;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.*;

/**
 * @类名： SysOrgController
 * @描述：【组织机构】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/13 11:24
 * @修改人：wolf
 * @修改时间：2019/7/13 11:24
 **/
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @GetMapping("/list")
    public Result list() {
        List<SysDept> deptList = sysDeptService.selectList(null);
        for (SysDept dept : deptList) {
            SysDept deptEntity = sysDeptService.selectById(dept.getParentId());
            if (deptEntity != null) {
                dept.setParentName(deptEntity.getName());
            }
        }
        return Result.success(deptList);
    }


    /**
     * @return com.struggler.common.entity.Result
     * @Author wolf
     * @Description 查询机构列表, 不分页
     * @Date 11:50 2019/7/13
     * @Param [params]
     **/
    @GetMapping("/listDept")
    public Result listDept(@RequestParam Map<String, Object> params) {
        return Result.success(sysDeptService.selectSysDept(params));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody SysDept sysDept){
        if(sysDept.getDeptType() < 9){
            sysDept.setName(sysDept.getCompanyName());
            sysDept.setOrgCode(sysDept.getComSerial());
        }
        sysDept.setCreatedDate(new Date());
        sysDept.setCreatedBy(999999L);
        sysDept.setUpdatedDate(new Date());
        sysDept.setUpdatedBy(999999L);
        sysDeptService.insert(sysDept);
        return Result.success(null);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result update(@RequestBody SysDept sysDept){
        if(sysDept.getDeptType() < 9){
            sysDept.setName(sysDept.getCompanyName());
            sysDept.setOrgCode(sysDept.getComSerial());
        }
        sysDeptService.updateById(sysDept);
        return Result.success(null);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") long id){
        //判断是否有子子部門
        List<SysDept> deptList = sysDeptService.queryListParentId(id);
        if(deptList.size() > 0){
            return Result.failed("请先删除子子部门");
        }
        sysDeptService.delete(new EntityWrapper<SysDept>().eq("id",id));
        return Result.success(null);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        SysDept sysDept = sysDeptService.selectById(id);
        return Result.success(sysDept);
    }

    /**
     * 组织结构数据
     */
    @PostMapping("/select")
    public Result info(@RequestBody SysDept sysDept){
        List<SysDept> list1 = new ArrayList<>();
        List<SysDept> list = sysDeptService.queryDeptByInfo(sysDept);
        if(StringUtils.isEmpty(sysDept.getComSerial())){
            //系统管理员登陆
            SysDept dept = new SysDept();
            dept.setName("组织根节点");
            dept.setId(0L);
            dept.setParentId(-1L);
            list1.add(dept);
            list1.addAll(list);
            return Result.success(list1);
        }
        return Result.success(list);
    }
}
