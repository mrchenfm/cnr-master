package com.ecut.cnr.view.mapper.news;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsTypeMapper
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/21 11:43
 */
@Mapper
@Component
public interface NewsTypeMapper extends BaseMapper<NewsType> {
}
