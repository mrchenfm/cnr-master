package com.ecut.cnr.view.config.elasticjob;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.List;

/**
 * @Classname JobSchedulerBuilder
 * @Description
 * @Date 2019/12/17 20:59
 * @Create by fangming_chen
 */
@Getter
public class JobSchedulerBuilder {

    private Object jobBean;

    private ElasticJobConfigParser elasticJobConf;

    private JobCoreConfiguration coreConfiguration;

    private CoordinatorRegistryCenter zookeeperRegistryCenter;

    private BeanDefinitionBuilder jobScheduler;

    JobSchedulerBuilder(Object jobBean, ElasticJobConfigParser elasticJobConf, JobCoreConfiguration coreConfig,
                        CoordinatorRegistryCenter zookeeperRegistryCenter) {
        this.jobBean = jobBean;
        this.elasticJobConf = elasticJobConf;
        this.coreConfiguration = coreConfig;
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }

    public void build() {
        jobScheduler = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class);
        jobScheduler.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        String jobClass = this.jobBean.getClass().getName();

        JobTypeConfiguration typeConfig = null;
        if (this.jobBean instanceof SimpleJob) {
            typeConfig = new SimpleJobConfiguration(this.coreConfiguration, jobClass);
            jobScheduler.addConstructorArgValue(jobBean);
        }
        jobScheduler.addConstructorArgValue(zookeeperRegistryCenter);
        jobScheduler.addConstructorArgValue(buildLiteJobConfiguration(typeConfig));

        // 去除日志入库 TODO: 后续统一指定一个日志库记录日志，定期按条件触发清理日志
        // appendEventTraceRdbDataSource();
    }

    public void appendConstructorArgValue(List<BeanDefinition> elasticJobListeners) {
        jobScheduler.addConstructorArgValue(elasticJobListeners);
    }


    private LiteJobConfiguration buildLiteJobConfiguration(JobTypeConfiguration typeConfig) {
        LiteJobConfiguration jobConfig;
        jobConfig = LiteJobConfiguration.newBuilder(typeConfig)
                .overwrite(elasticJobConf.isOverwrite())
                .build();
        return jobConfig;
    }
}

