package com.ecut.cnr.framework.dto.log;

import com.ecut.cnr.framework.request.sys.QueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: LoginLogSearchDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/12 15:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogSearchDto extends QueryRequest implements Serializable {

    private String userName;

    private Date loginStart;

    private Date loginEnd;

    private String loginTimeRange;
}
