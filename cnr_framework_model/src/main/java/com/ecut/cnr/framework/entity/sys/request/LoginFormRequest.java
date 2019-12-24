package com.ecut.cnr.framework.entity.sys.request;

import lombok.Data;

/**
 * @Classname LoginFormRequest
 * @Description 登入入参
 * @Date 2019/12/20 22:20
 * @Create by fangming_chen
 */
@Data
public class LoginFormRequest {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
