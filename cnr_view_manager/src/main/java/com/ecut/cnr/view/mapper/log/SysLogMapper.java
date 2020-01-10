package com.ecut.cnr.view.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.log.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname SysLogMapper
 * @Description
 * @Date 2020/01/05 21:55
 * @Create by fangming_chen
 */

@Component
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
}
