package com.ecut.cnr.view.auth.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname ShiroConfig
 * @Description
 * @Date 2019/12/15 19:48
 * @Create by fangming_chen
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //是否定时检查session
        sessionManager.setSessionValidationSchedulerEnabled(false);

        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(){
        return null;
    }

}
