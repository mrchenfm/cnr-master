package com.ecut.cnr.framework.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname SysLog
 * @Description 系统日志
 * @Date 2020/01/05 21:46
 * @Create by fangming_chen
 */
@Data
@TableName("t_sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作内容
     */
    @TableField("operation")
    private String operation;

    /**
     * 耗时
     */
    private Long spendTime;

    /**
     * 操作方法
     */
    @TableField("method")
    private String method;

    /**
     * 方法参数
     */
    @TableField("params")
    private String params;

    /**
     * 操作者IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作地点
     */
    private String location;

    private transient String createTimeFrom;
    private transient String createTimeTo;
}
