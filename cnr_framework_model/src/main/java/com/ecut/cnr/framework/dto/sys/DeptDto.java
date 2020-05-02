package com.ecut.cnr.framework.dto.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 16:55
 * @Description:
 */
@Data
public class DeptDto implements Serializable {

    private String id;

    /**
     * 上级部门 ID
     */
    private String parentId;

    /**
     * 部门名称
     */
    private String deptName;

    private String name;

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

    private List<DeptDto> children = new ArrayList<>();
}


