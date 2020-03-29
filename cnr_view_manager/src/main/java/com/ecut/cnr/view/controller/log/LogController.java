package com.ecut.cnr.view.controller.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.framework.entity.log.SysLog;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.log.ILoginLogService;
import com.ecut.cnr.view.service.log.ISysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/loginLog/list")
    @ResponseBody
    public Result getLoginLogs(QueryRequest queryRequest){
        try {
            IPage<LoginLog> allUsers = loginLogService.selectAllLoginLogs(queryRequest);
            Map<String, Object> dataTable = getDataTable(allUsers);

            //Object roleJson = JSONArray.toJSON(dataTable);
            Result result = Result.addMap(dataTable);

            return result;
        } catch (Exception e) {
            return new Result().put("msg","权限不足，请联系管理员授权");
        }
    }

    @GetMapping("/systemLog/list")
    @ResponseBody
    public Result getSystemLogs(QueryRequest queryRequest){
        IPage<SysLog> allSystemLogs = sysLogService.selectAllSystemLogs(queryRequest);
        Map<String, Object> dataTable = getDataTable(allSystemLogs);

        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }
}
