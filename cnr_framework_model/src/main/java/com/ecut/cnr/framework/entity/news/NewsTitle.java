package com.ecut.cnr.framework.entity.news;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsTitle
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 20:48
 */
@Data
@TableName("news_title")
@Builder
@NoArgsConstructor
public class NewsTitle implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String title;
    private String userId;
    private String  typeId;
    private Date  pubTime;
    private Integer readTimes;
    private Integer commentTimes;
    private String newsPic;
}
