package com.ecut.cnr.view.test;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ecut.cnr.framework.common.task.anno.ElasticSimpleJob;
import com.ecut.cnr.view.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname Task1
 * @Description
 * @Date 2019/12/14 17:16
 * @Create by fangming_chen
 */
@ElasticSimpleJob(
        jobName = "test",
        cron = "*/3 * * * * ?",
        shardingTotalCount = 1,
        overwrite = true
)
@Component
public class Task1 implements SimpleJob {

    @Autowired
    private ISysUserService sysUserService;
    @Override
    public void execute(ShardingContext shardingContext) {
        task();
    }



    public void task(){
        System.out.println(new Date()+"=="+sysUserService);
    }



}
