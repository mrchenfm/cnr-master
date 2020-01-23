package com.ecut.cnr.view.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.common.utils.AddressUtils;
import com.ecut.cnr.framework.common.utils.HttpContextUtil;
import com.ecut.cnr.framework.common.utils.IPUtils;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.view.mapper.log.LoginLogMapper;
import com.ecut.cnr.view.service.log.ILoginLogService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {
    @Autowired
    private IdUtils idUtils;

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtils.getCityInfo(ip));
        loginLog.setId(String.valueOf(idUtils.nextId()));
        this.save(loginLog);
    }
}
