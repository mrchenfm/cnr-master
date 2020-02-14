package com.ecut.cnr.view.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.dto.sys.SysUserDto;
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
    /**
     * 分页查询登入日志
     * @param page
     * @return
     */
    IPage<LoginLog> findAllLoginLogs(Page<UserInfoBO> page);
}
