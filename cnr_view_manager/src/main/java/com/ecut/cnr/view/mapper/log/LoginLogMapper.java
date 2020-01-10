package com.ecut.cnr.view.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.log.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname LoginLogMapper
 * @Description
 * @Date 2020/01/05 21:55
 * @Create by fangming_chen
 */

@Component
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}