package com.ecut.cnr.view.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.dto.log.LoginLogSearchDto;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.framework.request.sys.QueryRequest;

public interface ILoginLogService extends IService<LoginLog>  {
    /**
     * 保存用户登陆日志
     * @param loginLog
     */
    void saveLoginLog(LoginLog loginLog);

    /**
     * 分页查询登入日志
     * @param loginLogSearchDto
     * @return
     */
    IPage<LoginLog> selectAllLoginLogs(LoginLogSearchDto loginLogSearchDto);
}
