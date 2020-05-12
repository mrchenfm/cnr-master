package com.ecut.cnr.customer.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.customer.view.mapper.NewsTypeMapper;
import com.ecut.cnr.customer.view.service.INewsTypeService;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsTypeServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/26 20:24
 */
@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType> implements INewsTypeService {


    @Override
    public List<NewsType> getAll() {
        return this.baseMapper.selectList(null);
    }
}
