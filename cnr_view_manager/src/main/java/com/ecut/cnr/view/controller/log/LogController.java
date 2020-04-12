package com.ecut.cnr.view.controller.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.dto.log.LoginLogSearchDto;
import com.ecut.cnr.framework.dto.log.SysLogSearchDto;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.framework.entity.log.SysLog;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.log.ILoginLogService;
import com.ecut.cnr.view.service.log.ISysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 日志控制层
 */
@Controller
@RequestMapping("/admin")
public class LogController extends BaseController {
    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private ILoginLogService loginLogService;

    @RequestMapping("/loginLog/page")
    public String toPageLog(){
     return "log/loginLogList";
    }

    @RequestMapping("/systemLog/page")
    public String toPageSysLog(){
        return "log/systemLogList";
    }

    @RequiresPermissions("LOG_MANAGER")
    @PostMapping ("/loginLog/list")
    @ResponseBody
    public Result getLoginLogs(@RequestBody LoginLogSearchDto loginLogSearchDto){
        try {
            IPage<LoginLog> allUsers = loginLogService.selectAllLoginLogs(loginLogSearchDto);
            Map<String, Object> dataTable = getDataTable(allUsers);

            //Object roleJson = JSONArray.toJSON(dataTable);
            Result result = Result.addMap(dataTable);

            return result;
        } catch (Exception e) {
            return new Result().put("msg","权限不足，请联系管理员授权");
        }
    }

    @PostMapping("/systemLog/list")
    @ResponseBody
    public Result getSystemLogs(@RequestBody SysLogSearchDto sysLogSearchDto){
        IPage<SysLog> allSystemLogs = sysLogService.selectAllSystemLogs(sysLogSearchDto);
        Map<String, Object> dataTable = getDataTable(allSystemLogs);

        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }
}
