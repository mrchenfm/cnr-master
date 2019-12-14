package com.ecut.cnr.framework.config.elasticjob.autoconfig;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Classname SimpleJobAutoConfig
 * @Description
 * @Date 2019/12/14 17:32
 * @Create by fangming_chen
 */
@Configuration
// 这里有可能发现不了，这是因为类加载的顺序可能不一样，添加@AutoConfigureAfter注解
@AutoConfigureAfter(ZookeeperAutoConfig.class)
public class SimpleJobAutoConfig {

    @Autowired
    private CoordinatorRegistryCenter coordinatorRegistryCenter;

    @Autowired
    private ApplicationContext applicationContext;

    // 相当于 init 方法
    @PostConstruct
    public void  initSimpleJob(){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticSimpleJob.class);

        for (Map.Entry entry : beans.entrySet()){
            Object instance = entry.getValue();
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> superInterface : interfaces){
                if (superInterface == SimpleJob.class){
                    ElasticSimpleJob annotation = instance.getClass().getAnnotation(ElasticSimpleJob.class);
                    String jobName = annotation.jobName();
                    String cron = annotation.cron();
                    System.out.println(cron);
                    int shardingTotalCount = annotation.shardingTotalCount();
                    boolean overwrite = annotation.overwrite();

                    // 注册定时任务
                    // job核心配置
                    JobCoreConfiguration jcc = JobCoreConfiguration
                            // 再次修改cron表达式不会生效，这是因为第一次的cron表达式已经上传到zookeeper注册中心
                            // 需要在job根配置添加overwrite配置
                            .newBuilder(jobName, cron, shardingTotalCount)
                            .build();

                    // job类型配置
                    SimpleJobConfiguration jtc = new SimpleJobConfiguration(jcc, instance.getClass().getCanonicalName());

                    // job根的配置(LiteJobConfiguration)
                    LiteJobConfiguration ljc = LiteJobConfiguration
                            .newBuilder(jtc)
                            .overwrite(overwrite)
                            .build();

                    new JobScheduler(coordinatorRegistryCenter, ljc).init();
                }
            }
        }
    }

}
