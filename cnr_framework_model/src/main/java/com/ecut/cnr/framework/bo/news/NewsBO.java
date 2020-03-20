package com.ecut.cnr.framework.bo.news;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsBO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 21:13
 */
@Data
public class NewsBO implements Serializable {

    private String titleId;
    private String title;
    private String userId;
    private String  typeId;
    private String newsPic;
    private String context;
}
