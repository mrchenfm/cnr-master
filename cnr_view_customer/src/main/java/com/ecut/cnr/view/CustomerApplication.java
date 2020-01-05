package com.ecut.cnr.view;

import com.ecut.cnr.framework.common.utils.IdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Classname CustomerApplication
 * @Description
 * @Date 2020/01/05 13:48
 * @Create by fangming_chen
 */
@SpringBootApplication
@EnableTransactionManagement
public class CustomerApplication  extends WebMvcConfigurationSupport {

    @Value("${workerId}")
    private Integer workerId;

    @Value("${datacenterId}")
    private Integer datacenterId;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //这是配置模板页面的路径  配置文件里面已经配置了  所以这里暂时不需要
        //这是是配置静态资源文件的路径
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }


    @Bean
    public IdUtils dUtils(){
        return new IdUtils(workerId,datacenterId);
    }

    @Bean
    public Object testBean(DataSourceTransactionManager jobInitializeBean){
        System.out.println(">>>>>>>>>>" + jobInitializeBean.getClass().getName());
        return new Object();
    }

    public static void main(String[] args){
        SpringApplication.run(CustomerApplication.class,args);
    }
}
