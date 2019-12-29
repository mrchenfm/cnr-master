package com.ecut.cnr.view.service.sys;

import java.awt.image.BufferedImage;

/**
 * @Classname SysCaptchaService
 * @Description
 * @Date 2019/12/20 22:19
 * @Create by fangming_chen
 */
public interface ISysCaptchaService {
    /**
     * 验证图片
     * @param uuid
     * @return
     */
     BufferedImage getCaptcha(String uuid);

    /**
     * 验证验证码
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}
