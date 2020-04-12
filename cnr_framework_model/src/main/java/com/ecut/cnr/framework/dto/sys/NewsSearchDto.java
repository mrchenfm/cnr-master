package com.ecut.cnr.framework.dto.sys;

import com.ecut.cnr.framework.request.sys.QueryRequest;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsSearchDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/12 12:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NewsSearchDto extends QueryRequest implements Serializable {

    private Date pubStart;

    private Date pubEnd;

    private Date auditStart;

    private Date auditEnd;

    private String fromUser;

    private String auditUser;

    private String title;

    private Integer auditStatus;

    private String pubTimeRange;

    private String auditTimeRange;
}
