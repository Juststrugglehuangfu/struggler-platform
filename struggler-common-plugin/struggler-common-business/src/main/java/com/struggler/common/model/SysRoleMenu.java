package com.struggler.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Table: sys_role_menu
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     * Table:     sys_role_menu
     * Column:    id
     * Nullable:  false
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 删除状态[-1:删除，1:正常]
     *
     * Table:     sys_role
     * Column:    del
     * Nullable:  true
     */
    private Integer del;

    /**
     * 乐观锁
     *
     * Table:     sys_role
     * Column:    revision
     * Nullable:  true
     */
    private Integer revision;

    /**
     * 企业编号
     *
     * Table:     sys_role
     * Column:    comSerial
     * Nullable:  true
     */
    private String comSerial;

    /**
     * 企业ID
     *
     * Table:     sys_role
     * Column:    comSerialId
     * Nullable:  true
     */

    private Long comSerialId;

    /**
     * 角色ID
     *
     * Table:     sys_role_menu
     * Column:    role_id
     * Nullable:  true
     */
    private Long roleId;

    /**
     * 菜单ID
     *
     * Table:     sys_role_menu
     * Column:    menu_id
     * Nullable:  true
     */
    private Long menuId;

    /**
     * 创建人
     *
     * Table:     sys_role_menu
     * Column:    created_by
     * Nullable:  true
     */
    private Long createdBy;

    /**
     * 创建时间
     *
     * Table:     sys_role_menu
     * Column:    created_date
     * Nullable:  true
     */
    private Date createdDate;

    /**
     * 修改人
     *
     * Table:     sys_role_menu
     * Column:    updated_by
     * Nullable:  true
     */
    private Long updatedBy;

    /**
     * 修改时间
     *
     * Table:     sys_role_menu
     * Column:    updated_date
     * Nullable:  true
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role_menu
     *
     * @mbggenerated Tue Jun 04 14:37:07 CST 2019
     */
    private static final long serialVersionUID = 1L;



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbggenerated Tue Jun 04 14:37:07 CST 2019
     */
    public SysRoleMenu(Long id,Integer revision,Integer del,String comSerial,Long comSerialId, Long roleId, Long menuId, Long createdBy, Date createdDate, Long updatedBy, Date updatedDate) {
        this.id = id;
        this.del = del;
        this.revision = revision;
        this.comSerial = comSerial;
        this.comSerialId = comSerialId;
        this.roleId = roleId;
        this.menuId = menuId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbggenerated Tue Jun 04 14:37:07 CST 2019
     */
    public SysRoleMenu() {
        super();
    }
}
