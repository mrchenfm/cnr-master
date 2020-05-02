package com.ecut.cnr.view.mapper.meeting;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.meeting.MeetingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/29 12:05
 * @Description:
 */
@Mapper
@Component
public interface MeetingMapper extends BaseMapper<MeetingInfo> {
    List<MeetingInfo> getErpCalendarTaskByParams(@Param("roomId") String roomId, @Param("sTime") Date sTime, @Param("eTime") Date eTime);
}
