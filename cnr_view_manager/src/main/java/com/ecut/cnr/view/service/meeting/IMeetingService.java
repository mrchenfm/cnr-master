package com.ecut.cnr.view.service.meeting;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.meeting.MeetingInfo;
import com.ecut.cnr.framework.vo.CalendarTaskVo;
import java.util.Date;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/29 12:06
 * @Description:
 */
public interface IMeetingService extends IService<MeetingInfo> {

    List<CalendarTaskVo> getErpCalendarTaskByParams(String roomId, Date sTime, Date eTime);
}
