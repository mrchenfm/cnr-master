package com.ecut.cnr.view.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Classname KaptchaConfig
 * @Description 验证码配置类
 * @Date 2019/12/20 23:02
 * @Create by fangming_chen
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties=new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
