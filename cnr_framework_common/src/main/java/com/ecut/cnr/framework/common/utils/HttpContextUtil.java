package com.ecut.cnr.framework.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Classname HttpContextUtils
 * @Description  Http上下文
 * @Date 2019/12/15 21:25
 * @Create by fangming_chen
 */
public class HttpContextUtil {

    private HttpContextUtil(){

    }
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
