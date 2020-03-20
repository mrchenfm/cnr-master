package com.ecut.cnr.view;

import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.view.config.elasticjob.JobInitializeBean;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@MapperScan(value = "com.ecut.cnr.view.mapper.*")
@Import(FdfsClientConfig.class)
public class CnrApplication extends WebMvcConfigurationSupport {

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
    public JobInitializeBean getJobInitializeBean(){
        return new JobInitializeBean();
    }

    @Bean
    public Object testBean(DataSourceTransactionManager jobInitializeBean){
        System.out.println(">>>>>>>>>>" + jobInitializeBean.getClass().getName());
        return new Object();
    }

    public static void main(String[] args){
        SpringApplication.run(CnrApplication.class,args);
    }
}
