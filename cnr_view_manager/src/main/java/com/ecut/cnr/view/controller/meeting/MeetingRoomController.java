package com.ecut.cnr.view.controller.meeting;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.meeting.MeetingRoomInfo;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.meeting.IMeetingRoomService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: eos_master
 * @ClassName: MeetingRoomController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/29 22:07
 */
@Controller
@RequestMapping("/admin")
public class MeetingRoomController extends BaseController {

    @Autowired
    private IMeetingRoomService meetingRoomService;

    @Autowired
    private IdUtils idUtils;

    @RequestMapping("/room/manager")
    public String toRoomList(){
        return "meeting/roomList";
    }

    @RequestMapping("/rooms")
    @ResponseBody
    public Result rooms(@RequestBody QueryRequest queryRequest){
        Page<MeetingRoomInfo> page = new Page<>(queryRequest.getPage(),queryRequest.getLimit());
        IPage<MeetingRoomInfo> page1 = meetingRoomService.page(page, null);
        Map<String, Object> dataTable = getDataTable(page1);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);
        return result;
    }

    @RequestMapping("/roomAdd")
    public String toAddRoom(){
        return "meeting/addRoom";
    }
    @RequestMapping("/addRoom")
    @ResponseBody
    public Result addRoom(@RequestBody MeetingRoomInfo meetingRoomInfo){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO principal = (UserInfoBO) subject.getPrincipal();
        meetingRoomInfo.setRoomId(String.valueOf(idUtils.nextId()));
        meetingRoomInfo.setCreateBy(principal.getId());
        meetingRoomInfo.setUpdateBy(principal.getId());
        meetingRoomInfo.setCreateDate(new Date());
        meetingRoomInfo.setUpdateDate(new Date());
        boolean b = meetingRoomService.saveOrUpdate(meetingRoomInfo);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.ok();
        if(!b){
            return Result.error("添加会议室失败");
        }
        return result;
    }
    @RequestMapping("/roomUpdate")
    public String toUpdateRoom(Model model,String id){
        MeetingRoomInfo byId = meetingRoomService.getById(id);
        model.addAttribute("room",byId);
        return "meeting/updateRoom";
    }

    @RequestMapping("/update/room")
    @ResponseBody
    public Result updateRoom(@RequestBody MeetingRoomInfo meetingRoomInfo){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO principal = (UserInfoBO) subject.getPrincipal();
        meetingRoomInfo.setUpdateBy(principal.getId());
        meetingRoomInfo.setUpdateDate(new Date());
        boolean b = meetingRoomService.saveOrUpdate(meetingRoomInfo);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.ok();
        if(!b){
            return Result.error("修改会议室失败");
        }
        return result;
    }

    @RequestMapping("/delete/room")
    @ResponseBody
    public Result deleteRoom(String id){

        boolean b = meetingRoomService.removeById(id);

        Result result = Result.ok();
        if(!b){
            return Result.error("删除会议室失败");
        }
        return result;
    }
}
