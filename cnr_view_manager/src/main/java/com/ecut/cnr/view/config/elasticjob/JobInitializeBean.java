package com.ecut.cnr.view.config.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.ecut.cnr.framework.common.task.anno.ElasticSimpleJob;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Classname JobInitializeBean
 * @Description
 * @Date 2019/12/17 21:03
 * @Create by fangming_chen
 */
@Slf4j
@NoArgsConstructor
public class JobInitializeBean  implements ApplicationContextAware {


    @Autowired
    private CoordinatorRegistryCenter zkCenter;




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        initializeJobBeans(applicationContext);
    }

    private void initializeJobBeans(ApplicationContext ctx) {
        Map<String, Object> beanMap = ctx.getBeansWithAnnotation(ElasticSimpleJob.class);
        for (Object confBean : beanMap.values()) {
            initializeJobBean(ctx, confBean);
        }
    }

    private void initializeJobBean(ApplicationContext ctx, Object jobBean) {

        Class<?> clz = jobBean.getClass();
        ElasticSimpleJob elasticJob = clz.getAnnotation(ElasticSimpleJob.class);
        Assert.notNull(elasticJob, "[ElasticJobConfig] Job bean's ElasticJobConfig could not be null...");
        String jobName = elasticJob.jobName();

        ElasticJobConfigParser elasticJobConfigParser = getElasticJobConf(ctx, elasticJob, jobName);

        JobCoreConfiguration coreConfig = getJobCoreConfiguration(jobName, elasticJobConfigParser);
        JobSchedulerBuilder jobSchedulerBuilder = new JobSchedulerBuilder(jobBean, elasticJobConfigParser, coreConfig,
                zkCenter);
        jobSchedulerBuilder.build();

        List<BeanDefinition> elasticJobListeners = getTargetElasticJobListeners(elasticJobConfigParser);
        //jobSchedulerBuilder.appendConstructorArgValue(elasticJobListeners);
        jobSchedulerBuilder.appendConstructorArgValue(elasticJobListeners);

        registerSchedulerToSpringContainer(ctx, jobSchedulerBuilder);


    }

    private List<BeanDefinition> getTargetElasticJobListeners(ElasticJobConfigParser elasticJobConfigParser) {
        List<BeanDefinition> result = new ManagedList<>(2);
        if (!StringUtils.isEmpty(elasticJobConfigParser.getListener())) {
            BeanDefinitionBuilder factory = buildListener(elasticJobConfigParser);
            result.add(factory.getBeanDefinition());
        }

        if (!StringUtils.isEmpty(elasticJobConfigParser.getDistributedListener())) {
            BeanDefinitionBuilder factory = buildDistributedListener(elasticJobConfigParser);
            result.add(factory.getBeanDefinition());
        }
        return result;
    }

    private BeanDefinitionBuilder buildListener(ElasticJobConfigParser elasticJobConfigParser) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(elasticJobConfigParser.getListener());
        factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        return factory;
    }

    private BeanDefinitionBuilder buildDistributedListener(ElasticJobConfigParser elasticJobConfigParser) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(elasticJobConfigParser.getDistributedListener());
        factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        factory.addConstructorArgValue(elasticJobConfigParser.getStartedTimeoutMilliseconds());
        factory.addConstructorArgValue(elasticJobConfigParser.getCompletedTimeoutMilliseconds());
        return factory;
    }

    private JobCoreConfiguration getJobCoreConfiguration(String jobName, ElasticJobConfigParser elasticJobConfigParser) {

        return JobCoreConfiguration.newBuilder(jobName, elasticJobConfigParser.getCron(), elasticJobConfigParser.getShardingTotalCount())
                .build();
    }

    private ElasticJobConfigParser getElasticJobConf(ApplicationContext ctx, ElasticSimpleJob elasticJob, String jobName) {
        ElasticJobConfigParser elasticJobConf = new ElasticJobConfigParser(ctx.getEnvironment(), jobName, elasticJob);
        elasticJobConf.parseElasticJobConf();
        return elasticJobConf;
    }

    private void registerSchedulerToSpringContainer(ApplicationContext ctx, JobSchedulerBuilder jobScheduler1) {
        String jobName = jobScheduler1.getElasticJobConf().getJobName();
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)
                ctx.getAutowireCapableBeanFactory();

        defaultListableBeanFactory.registerBeanDefinition(jobName + SpringJobScheduler.class.getSimpleName(),
                jobScheduler1.getJobScheduler().getBeanDefinition());

        SpringJobScheduler springJobScheduler = null;
        try {
            springJobScheduler = (SpringJobScheduler) ctx.getBean(jobName + SpringJobScheduler.class.getSimpleName());
            log.info("{}",ctx);
        } catch (BeansException e) {
            log.info("{},etx={}",e,ctx);
        }

        springJobScheduler.init();

        log.info("[" + jobName + "] " + jobScheduler1.getJobBean().getClass().getName() + " register to Spring Container success...");
    }

}