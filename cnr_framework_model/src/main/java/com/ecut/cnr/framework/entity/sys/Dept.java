package com.ecut.cnr.framework.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Dept
 * @Description
 * @Date 2020/01/05 21:27
 * @Create by fangming_chen
 */
@Data
@TableName("t_sys_dept")
public class Dept implements Serializable{

    private static final long serialVersionUID = 5702271568363798328L;
    /**
     * 部门 ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 上级部门 ID
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String deptName;

    private String masterName;


    private String master;

    private String phone;

    private String fax;

    private String email;

    /**
     * 排序
     */
    private Long orderNum;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remark;

    private Integer delFlag;
}
