package com.ecut.cnr.framework.dto.sys;

import com.ecut.cnr.framework.request.sys.QueryRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: RoleSearchDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/12 12:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleSearchDto extends QueryRequest implements Serializable {
    private String roleName;
}
