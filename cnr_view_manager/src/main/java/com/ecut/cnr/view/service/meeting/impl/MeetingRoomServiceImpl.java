package com.ecut.cnr.view.service.meeting.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.meeting.MeetingRoomInfo;
import com.ecut.cnr.view.mapper.meeting.MeetingRoomMapper;
import com.ecut.cnr.view.service.meeting.IMeetingRoomService;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @ProjectName: eos_master
 * @ClassName: MeetingRoomServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/29 22:18
 */
@Service
public class MeetingRoomServiceImpl extends ServiceImpl<MeetingRoomMapper, MeetingRoomInfo> implements IMeetingRoomService {
}
