package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.entity.sys.Dept;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.view.mapper.sys.DeptMapper;
import com.ecut.cnr.view.mapper.sys.SysUserMapper;
import com.ecut.cnr.view.service.sys.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 17:48
 * @Description:
 */
@Service
@Slf4j
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result deleteById(String id) {

        int i = this.baseMapper.deleteById(id);
        log.info("删除数据：{}条",i);
        return new Result();
    }

    @Transactional
    @Override
    public Result saveDeptMaster(Dept dept, SysUser sysUser) {
        int i = this.baseMapper.updateById(dept);
        if(i>0){
            sysUserMapper.updateById(sysUser);
            return Result.ok();
        }
        return Result.error("添加负责人失败");
    }
}
