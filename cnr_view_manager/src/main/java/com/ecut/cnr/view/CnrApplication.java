package com.ecut.cnr.view;

import com.ecut.cnr.view.config.elasticjob.JobInitializeBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Classname CnrApplication
 * @Description 管理界面启动类
 * @Date 2019/12/14 12:05
 * @Create by fangming_chen
 */
@SpringBootApplication
@EnableScheduling
public class CnrApplication extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //这是配置模板页面的路径  配置文件里面已经配置了  所以这里暂时不需要
        //这是是配置静态资源文件的路径
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public JobInitializeBean getJobInitializeBean(){
        return new JobInitializeBean();
    }

    public static void main(String[] args){
        SpringApplication.run(CnrApplication.class,args);
    }
}
