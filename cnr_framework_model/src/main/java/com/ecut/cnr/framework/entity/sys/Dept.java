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
@TableName("t_dept")
public class Dept implements Serializable{

    private static final long serialVersionUID = 5702271568363798328L;
    /**
     * 部门 ID
     */
    @TableId(value = "dept_id")
    private Long deptId;

    /**
     * 上级部门 ID
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Long orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
