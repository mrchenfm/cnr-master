package com.ecut.cnr.view.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.log.LoginLog;

public interface ILoginLogService extends IService<LoginLog>  {
    /**
     * 保存用户登陆日志
     * @param loginLog
     */
    void saveLoginLog(LoginLog loginLog);
}
