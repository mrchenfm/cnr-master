package com.ecut.cnr.view.service.log.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.utils.AddressUtils;
import com.ecut.cnr.framework.common.utils.HttpContextUtil;
import com.ecut.cnr.framework.common.utils.IPUtils;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
import com.ecut.cnr.framework.entity.log.LoginLog;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.mapper.log.LoginLogMapper;
import com.ecut.cnr.view.service.log.ILoginLogService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public IPage<LoginLog> selectAllLoginLogs(QueryRequest queryRequest) {
        Page<UserInfoBO> page = new Page<>(queryRequest.getPage(), queryRequest.getLimit());
        //this.baseMapper.findAllUsersPage(page);
        IPage<LoginLog> allUsers = this.baseMapper.findAllLoginLogs(page);
        List<LoginLog> records = allUsers.getRecords();
        allUsers.setRecords(records);
        return allUsers;
    }
}
