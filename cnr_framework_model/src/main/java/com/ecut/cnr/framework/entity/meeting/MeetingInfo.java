package com.ecut.cnr.framework.entity.meeting;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 09:27
 * @Description:
 */
@Data
@TableName(value = "t_meeting_info")
public class MeetingInfo implements Serializable {

    private String id;

    /**
     * 预约主题
     */
    private String appointTheme;
    /**
     * 预约人
     */
    private String appointPerson;
    /**
     * 预约结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date etime;
    /**
     * 预约开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date stime;
    /**
     * 联系方式
     */

    private String tel;

    private String meetingContext;


    private Integer isFinish;

    private String roomId;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createDate;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateDate;

    private String joinEmp;

}
