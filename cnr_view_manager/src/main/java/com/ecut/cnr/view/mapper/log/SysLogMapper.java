package com.ecut.cnr.view.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.log.SysLogSearchDto;
import com.ecut.cnr.framework.entity.log.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    /**
     * 分页查询操作日志
     * @param page
     * @return
     */
    IPage<SysLog> findAllSystemLogs(Page<UserInfoBO> page, @Param("sysLogSearchDto") SysLogSearchDto sysLogSearchDto);
}
