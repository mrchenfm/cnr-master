package com.ecut.cnr.framework.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname LoginLog
 * @Description 登徒日志实体
 * @Date 2020/01/05 21:46
 * @Create by fangming_chen
 */
@Data
@TableName("t_login_log")
@Slf4j
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 921991157363932095L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 登录用户
     */
    private String username;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录地点
     */
    private String location;
    /**
     * 登录 IP
     */
    private String ip;
    /**
     * 操作系统
     */
    private String system;
    /**
     * 登录浏览器
     */
    private String browser;

    private transient String loginTimeFrom;
    private transient String loginTimeTo;


}
