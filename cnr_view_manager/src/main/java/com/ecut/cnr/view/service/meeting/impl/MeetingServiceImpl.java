package com.ecut.cnr.view.service.meeting.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.meeting.MeetingInfo;
import com.ecut.cnr.framework.vo.CalendarTaskVo;
import com.ecut.cnr.view.mapper.meeting.MeetingMapper;
import com.ecut.cnr.view.service.meeting.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/29 12:07
 * @Description:
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, MeetingInfo> implements IMeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Override
    public List<CalendarTaskVo> getErpCalendarTaskByParams(String roomId, Date sTime, Date eTime) {
        List<MeetingInfo> list = meetingMapper.getErpCalendarTaskByParams(roomId, sTime, eTime);
        List<CalendarTaskVo> voList = new ArrayList<>();
        CalendarTaskVo vo;
        if(list.size() > 0) {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            for(MeetingInfo task: list) {
                vo = new CalendarTaskVo();
                vo.setId(task.getId());
                vo.setTitle(task.getAppointTheme());
                vo.setStart(formatter.format(task.getStime()));
                vo.setEnd(formatter.format(task.getEtime()));
                Map<String, Object> map = new HashMap<>();
                map.put("appointPerson", task.getAppointPerson());
                map.put("tel", task.getTel());
                vo.setExtendedProps(map);
                voList.add(vo);
            }
        }
        return voList;
    }
}
