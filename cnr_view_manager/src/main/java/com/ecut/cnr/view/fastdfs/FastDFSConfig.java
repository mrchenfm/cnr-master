package com.ecut.cnr.view.fastdfs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/3 09:08
 * @Description:
 */
@Configuration
@ConditionalOnProperty(value = "spring.fastdfs.enabled",havingValue ="enable",matchIfMissing = false)
public class FastDFSConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.fastdfs")
    public FastDFSFile fastDFSFile(){
        return new FastDFSFile();
    }

    @Bean(initMethod = "init")
    @ConditionalOnBean(FastDFSFile.class)
    public FastDFSService fastDFSService(){
        return new FastDFSService();
    }



}
