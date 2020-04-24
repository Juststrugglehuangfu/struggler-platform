package com.struggler.authorization.model;

import com.struggler.common.model.SysDept;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @类名： SysDeptVo
 * @描述：【部门Vo】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/13 15:23
 * @修改人：wolf
 * @修改时间：2019/7/13 15:23
 **/
@Data
public class SysDeptVo extends SysDept {

    private List<SysDeptVo> children;
    
}
