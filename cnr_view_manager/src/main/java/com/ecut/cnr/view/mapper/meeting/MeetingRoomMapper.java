package com.ecut.cnr.view.mapper.meeting;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.meeting.MeetingRoomInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: eos_master
 * @ClassName: MeetingRoomMapper
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/29 22:15
 */
@Mapper
@Component
public interface MeetingRoomMapper extends BaseMapper<MeetingRoomInfo> {
}
