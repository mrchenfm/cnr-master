package com.ecut.cnr.view.controller.meeting;

import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.ResultBean;
import com.ecut.cnr.framework.common.anno.ControllerEndpoint;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.meeting.MeetingInfo;
import com.ecut.cnr.framework.entity.meeting.MeetingRoomInfo;
import com.ecut.cnr.framework.vo.CalendarTaskVo;
import com.ecut.cnr.view.service.meeting.IMeetingRoomService;
import com.ecut.cnr.view.service.meeting.IMeetingService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/29 10:19
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class MeetingController {

    @Autowired
    private IMeetingService meetingService;

    @Autowired
    private IMeetingRoomService meetingRoomService;

    @Autowired
    private IdUtils idUtils;

    @RequestMapping("/calendar")
    public String toCalendar(Model model){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO principal = (UserInfoBO) subject.getPrincipal();
        List<MeetingRoomInfo> list = meetingRoomService.list(null);
        model.addAttribute("room",list);
        model.addAttribute("userInfo",principal);
        return "calendar/index";
    }


    @ControllerEndpoint(operation = "添加会议", exceptionMessage = "添加会议异常")
    @RequiresPermissions("MEETING_ADD")
    @RequestMapping("/meeting/create")
    @ResponseBody
    public ResultBean<String> createMeeting(@RequestBody MeetingInfo meetingInfo){
        meetingInfo.setId(String.valueOf(idUtils.nextId()));
        meetingInfo.setUpdateBy(meetingInfo.getCreateBy());
        meetingInfo.setCreateDate(new Date());
        meetingInfo.setUpdateDate(new Date());
        if(meetingInfo == null || meetingInfo.getRoomId() == null){
            ResultBean<String> resultBean = new ResultBean<>("请选择会议室");
            resultBean.setCode(9999);
            return resultBean;
        }
        boolean b = meetingService.saveOrUpdate(meetingInfo);
        if(!b){
            ResultBean<String> resultBean = new ResultBean<String>("会议添加失败");
            resultBean.setCode(9999);
            return resultBean;
        }
        return new ResultBean<String>("");
    }

    @GetMapping("/meeting/getByParams")
    @ResponseBody
    public ResultBean<List<CalendarTaskVo>> getErpCalendarTaskByParams(String roomId, String sTime, String eTime) {
        Date st = new Date(Long.parseLong(sTime));
        Date et = new Date(Long.parseLong(eTime));
        List<CalendarTaskVo> list = meetingService.getErpCalendarTaskByParams(roomId, st, et);
        return new ResultBean<List<CalendarTaskVo>>(list);
    }

    @ControllerEndpoint(operation = "修改会议", exceptionMessage = "修改会议异常")
    @RequiresPermissions("MEETING_UPDATE")
    @RequestMapping(value = "/meeting/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<Boolean> update(@RequestBody @Validated MeetingInfo item) {
        MeetingInfo byId = meetingService.getById(item.getId());
        if(!byId.getCreateBy().equals(item.getCreateBy())){
            return new ResultBean<>(9999);
        }
        boolean b = meetingService.updateById(item);
        return new ResultBean<Boolean>(b);
    }

    @ControllerEndpoint(operation = "删除会议", exceptionMessage = "删除会议异常")
    @RequiresPermissions("MEETING_DELETE")
    @RequestMapping(value = "/meeting/deleteByID", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<Boolean> delete(String id) {
        MeetingInfo byId = meetingService.getById(id);
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO principal = (UserInfoBO) subject.getPrincipal();
        if(!byId.getCreateBy().equals(principal.getId())){
            return new ResultBean<>(9999);
        }
        boolean result = meetingService.removeById(id);
        return new ResultBean<Boolean>(result);
    }
}
