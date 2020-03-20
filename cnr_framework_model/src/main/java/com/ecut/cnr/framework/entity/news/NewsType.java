package com.ecut.cnr.framework.entity.news;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsType
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 20:52
 */
@Data
@TableName("news_type")
public class NewsType implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String typeName;
}
