package com.ecut.cnr.view.auth;

import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.common.utils.HttpContextUtils;
import com.ecut.cnr.framework.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname OAuth2Filter
 * @Description
 * 该filter的作用类似于FormAuthenticationFilter用于oauth2客户端的身份验证控制；
 * 如果当前用户还没有身份验证，首先会判断url中是否有code（服务端返回的auth code），
 * 如果没有则重定向到服务端进行登录并授权，然后返回auth code；
 * 接着OAuth2AuthenticationFilter会用auth code创建OAuth2Token，然后提交给Subject.login进行登录；
 * 接着OAuth2Realm会根据OAuth2Token进行相应的登录逻辑。
 * @Date 2019/12/15 20:06
 * @Create by fangming_chen
 */
@Slf4j
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求的token
        String token = getRequestToken((HttpServletRequest)servletRequest);
        if(StringUtils.isEmpty(token)){
            return null;
        }
        return new OAuh2Token(token);
    }

    /**
     *获取请求的token
     * @param servletRequest
     * @return
     */
    private String getRequestToken(HttpServletRequest servletRequest) {

        //获取token中的token
        String token = servletRequest.getHeader("token");
        //如果token中不存在token，则从参数中获取token
        if(StringUtils.isEmpty(token)){
            token = servletRequest.getParameter("token");
        }
        return token;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(((HttpServletRequest)request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(StringUtils.isEmpty(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Access-Control-Allow-Credentials","true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
            String json = JsonUtils.toJson(Result.error(ErrorEnum.INVALID_TOKEN));
            httpResponse.getWriter().print(json);

            return false;
        }
        return executeLogin(servletRequest,servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result r = Result.error(ErrorEnum.NO_AUTH.getCode(),throwable.getMessage());
            String json = JsonUtils.toJson(r);
            httpResponse.getWriter().print(json);
        }catch (Exception e1){
            log.error("出现异常：{}",e1);
        }
        return false;
    }


}
