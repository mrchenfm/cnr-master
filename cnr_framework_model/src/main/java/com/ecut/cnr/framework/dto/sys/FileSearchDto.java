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
 * @ClassName: FileSearchDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 12:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileSearchDto extends QueryRequest implements Serializable {
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

    private String createTime;

    private String fileType;
}
