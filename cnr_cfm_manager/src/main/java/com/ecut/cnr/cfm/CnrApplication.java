package com.ecut.cnr.cfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname CnrApplication
 * @Description 管理界面启动类
 * @Date 2019/12/14 12:05
 * @Create by fangming_chen
 */
@SpringBootApplication
@EnableScheduling
public class CnrApplication {

    public static void main(String[] args){
        SpringApplication.run(CnrApplication.class,args);
    }
}
