package com.ecut.cnr.framework.dto.sys;

import com.ecut.cnr.framework.request.sys.QueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: UserSearchDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/12 10:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSearchDto extends QueryRequest implements Serializable {

    /**
     * 注册开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date start;

    /**
     * 组测结束日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date end;

    private String registerTimeRange;

    /**
     * 用户名
     */
    private String username;

    /**
     * 是否激活
     */
    private Integer enabled;
}
