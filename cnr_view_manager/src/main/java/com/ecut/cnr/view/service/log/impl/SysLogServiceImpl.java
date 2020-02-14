package com.ecut.cnr.view.service.log.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.utils.AddressUtils;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.log.SysLog;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.mapper.log.SysLogMapper;
import com.ecut.cnr.view.service.log.ISysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Classname SysLogServiceImpl
 * @Description
 * @Date 2020/01/07 19:40
 * @Create by fangming_chen
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements ISysLogService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IdUtils idUtils;

    @Override
    public void saveLog(ProceedingJoinPoint point, Method targetMethod, String ip, String operation, long start) {
        SysLog sysLog = new SysLog();
        // 设置 IP地址
        sysLog.setIp(ip);
        // 设置操作用户
        UserInfoBO user = (UserInfoBO) SecurityUtils.getSubject().getPrincipal();
        if (user != null)
            sysLog.setUsername(user.getUsername());
        // 设置耗时
        sysLog.setSpendTime(System.currentTimeMillis() - start);
        // 设置操作描述
        sysLog.setOperation(operation);
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = targetMethod.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(targetMethod);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            sysLog.setParams(params.toString());
        }
        sysLog.setCreateTime(new Date());
        sysLog.setLocation(AddressUtils.getCityInfo(ip));
        sysLog.setId(String.valueOf(idUtils.nextId()));
        // 保存系统日志
        save(sysLog);
    }

    @Override
    public IPage<SysLog> selectAllSystemLogs(QueryRequest queryRequest) {
        Page<UserInfoBO> page = new Page<>(queryRequest.getPage(), queryRequest.getLimit());
        //this.baseMapper.findAllUsersPage(page);
        IPage<SysLog> allUsers = this.baseMapper.findAllSystemLogs(page);
        List<SysLog> records = allUsers.getRecords();
        allUsers.setRecords(records);
        return allUsers;
    }

    @SuppressWarnings("all")
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Set set = ((Map) args[i]).keySet();
                    List<Object> list = new ArrayList<>();
                    List<Object> paramList = new ArrayList<>();
                    for (Object key : set) {
                        list.add(((Map) args[i]).get(key));
                        paramList.add(key);
                    }
                    return handleParams(params, list.toArray(), paramList);
                } else {
                    if (args[i] instanceof Serializable) {
                        Class<?> aClass = args[i].getClass();
                        try {
                            aClass.getDeclaredMethod("toString", new Class[]{null});
                            // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                        } catch (NoSuchMethodException e) {
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                        }
                    } else if (args[i] instanceof MultipartFile) {
                        MultipartFile file = (MultipartFile) args[i];
                        params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                    } else {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                    }
                }
            }
        } catch (Exception ignore) {
            params.append("参数解析失败");
        }
        return params;
    }
}
