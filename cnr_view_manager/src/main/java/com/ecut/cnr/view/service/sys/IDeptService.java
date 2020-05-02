package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.entity.sys.Dept;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/28 16:35
 * @Description:
 */
public interface IDeptService extends IService<Dept> {

    Result deleteById(String id);

    Result saveDeptMaster(Dept dept);
}
