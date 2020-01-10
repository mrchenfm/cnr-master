package com.ecut.cnr.view.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname DeptMapper
 * @Description
 * @Date 2020/01/05 21:29
 * @Create by fangming_chen
 */
@Mapper
@Component
public interface DeptMapper extends BaseMapper<Dept> {
}
