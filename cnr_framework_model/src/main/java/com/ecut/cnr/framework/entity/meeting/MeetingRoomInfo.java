package com.ecut.cnr.framework.entity.meeting;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 10:18
 * @Description:
 */
@Data
@TableName(value = "t_meeting_room_info")
public class MeetingRoomInfo implements Serializable {


    @TableId(value = "room_id")
    private String roomId;

    private String roomName;

    private String roomAddress;
    private Integer isMultiMedia;
    private Integer maxPerson;

    private String createBy;

    @TableField(exist = false)
    private String createUser;

    private Date createDate;

    private String updateBy;

    private Date updateDate;
}
