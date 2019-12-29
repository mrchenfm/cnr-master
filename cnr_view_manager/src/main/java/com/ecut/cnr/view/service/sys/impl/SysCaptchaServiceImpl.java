package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ecut.cnr.framework.common.constants.RedisKeyConstants;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.common.exception.MyException;
import com.ecut.cnr.view.service.sys.ISysCaptchaService;
import com.ecut.cnr.view.utils.RedisUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @Classname SysCaptchaServiceImpl
 * @Description
 * @Date 2019/12/20 22:24
 * @Create by fangming_chen
 */
@Service
public class SysCaptchaServiceImpl implements ISysCaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    /**  验证码过期时长5秒 */
    public final static long CAPTCHA_EXPIRE = 60 * 15;
    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isEmpty(uuid)){
            throw new MyException(ErrorEnum.NO_UUID);
        }
        //生成文字验证码
        String code = producer.createText();
        // 存进redis,5分钟后过期
        redisUtils.set(genRedisKey(uuid),code,CAPTCHA_EXPIRE);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        if(StringUtils.isEmpty(uuid)||StringUtils.isEmpty(code)){
            return false;
        }
        // 从redis中取
        String redisKey=genRedisKey(uuid);
        String captchaCode=redisUtils.get(redisKey);
        //删除验证码
        redisUtils.delete(redisKey);
        if(code.equalsIgnoreCase(captchaCode)){
            return true;
        }
        return false;
    }

    /**
     * 生成redis key
     * @param uuid
     * @return
     */
    private String genRedisKey(String uuid){
        return RedisKeyConstants.MANAGE_SYS_CAPTCHA+uuid;
    }
}
