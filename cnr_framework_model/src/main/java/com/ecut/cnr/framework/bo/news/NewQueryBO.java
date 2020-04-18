package com.ecut.cnr.framework.bo.news;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewQueryBO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/23 21:10
 */
@Data
public class NewQueryBO implements Serializable {
    private String id;
    private String title;
    private String userName;
    private String  typeName;
    private String newsPic;
    private String context;
    private Date pubTime;
    private Integer readTimes;
    private Integer commentTimes;
    private Integer auditStatus;
    private String auditStatusName;
    private String auditName;
    private Date auditTime;

    private String rejectReason;
}
