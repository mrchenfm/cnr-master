package com.ecut.cnr.view.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Classname OAuh2Token
 * @Description 类似于UsernamePasswordToken和CasToken；用于存储oauth2服务端返回的auth code。
 * @Date 2019/12/15 20:08
 * @Create by fangming_chen
 */
public class OAuh2Token implements AuthenticationToken {

    private String token;

    public OAuh2Token(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
