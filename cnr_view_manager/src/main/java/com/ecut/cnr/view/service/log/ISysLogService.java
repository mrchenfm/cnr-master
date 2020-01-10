package com.ecut.cnr.view.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.entity.log.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.lang.reflect.Method;

/**
 * @Classname ISysLogService
 * @Description
 * @Date 2020/01/05 22:18
 * @Create by fangming_chen
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 保存系统日志
     * @param point
     * @param targetMethod
     * @param ip
     * @param operation
     * @param start
     */
    @Async(CnrContants.ASYNC_POOL)
    void saveLog(ProceedingJoinPoint point, Method targetMethod, String ip, String operation, long start);
}
