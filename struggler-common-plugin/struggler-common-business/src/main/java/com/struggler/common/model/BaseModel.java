package com.struggler.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @类名： BaseModel
 * @描述：【基础model】
 * @修改描述：【修改描述】
 * @版本：1.0
 * @创建人：wolf
 * @创建时间：2019/7/11 16:06
 * @修改人：wolf
 * @修改时间：2019/7/11 16:06
 **/
@Data
public class BaseModel implements Serializable {
    /**
     * 企业编号
     * <p>
     * Column:    comSerial
     * Nullable:  true
     */
    private String comSerial;
    /**
     * 企业ID
     * <p>
     * Column:    comSerialId
     * Nullable:  true
     */
    private Long comSerialId;
    /**
     * 乐观锁
     * <p>
     * Column:    revision
     * Nullable:  true
     */
    @Version
    private Long revision;
    /**
     * 创建人
     * <p>
     * Column:    createdBy
     * Nullable:  true
     */
    private Long createdBy;
    /**
     * 创建时间
     * <p>
     * Column:    createdDate
     * Nullable:  true
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    /**
     * 更新人
     * <p>
     * Column:    updatedBy
     * Nullable:  true
     */
    private Long updatedBy;
    /**
     * 更新时间
     * <p>
     * Column:    updatedDate
     * Nullable:  true
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
}
