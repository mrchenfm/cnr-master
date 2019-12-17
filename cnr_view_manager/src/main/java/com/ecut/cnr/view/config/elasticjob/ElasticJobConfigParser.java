package com.ecut.cnr.view.config.elasticjob;

import com.ecut.cnr.framework.common.task.anno.ElasticSimpleJob;
import com.ecut.cnr.framework.common.utils.SpringEnvPropertyUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;

/**
 * @Classname ElasticJobConfigParser
 * @Description
 * @Date 2019/12/17 20:55
 * @Create by fangming_chen
 */
@Getter
@Setter
public class ElasticJobConfigParser {


        private Environment environment;

        private ElasticSimpleJob elasticJobConfig;

        private static final String KEY_PREFIX = "elastic.job.";

        ElasticJobConfigParser(Environment environment, String jobName, ElasticSimpleJob elasticJob) {
            this.environment = environment;
            this.jobName = jobName;
            this.elasticJobConfig = elasticJob;
        }

        private String buildKey(String fieldName) {
            return KEY_PREFIX + jobName + "." + fieldName;
        }

        /**
         * 获取配置值，优先级：配置文件 > 注解属性
         */
        void parseElasticJobConf() {
            setCron(SpringEnvPropertyUtils.getPropertyStringValue(environment, buildKey("cron"), elasticJobConfig.cron()));
            setOverwrite(SpringEnvPropertyUtils.getPropertyBooleanValue(environment, buildKey("overwrite"), elasticJobConfig.overwrite()));
            setListener(SpringEnvPropertyUtils.getPropertyStringValue(environment, buildKey("listener"), elasticJobConfig.listener()));
            setDistributedListener(SpringEnvPropertyUtils.getPropertyStringValue(environment, buildKey("listener"), elasticJobConfig.distributedListener()));
            setShardingTotalCount(SpringEnvPropertyUtils.getPropertyIntegerValue(environment, buildKey("shardingTotalCount"), elasticJobConfig.shardingTotalCount()));
            setStartedTimeoutMilliseconds(SpringEnvPropertyUtils.getPropertyLongValue(environment, buildKey("startedTimeoutMilliseconds"), elasticJobConfig.startedTimeoutMilliseconds()));
            setCompletedTimeoutMilliseconds(SpringEnvPropertyUtils.getPropertyLongValue(environment, buildKey("completedTimeoutMilliseconds"), elasticJobConfig.completedTimeoutMilliseconds()));
        }

	/* --------------------SimpleJobConfiguration属性详细说明 start-------------------- */

        /**
         * 作业名称
         */
        private String jobName;

        /**
         * cron表达式，用于控制作业触发时间
         */
        private String cron;

        /**
         * 作业分片总数
         */
        private int shardingTotalCount;

        private boolean overwrite;

        /**
         * 前置后置任务监听实现类，需实现ElasticJobListener接口
         */
        private String listener;

        /**
         * 前置后置任务分布式监听实现类，需继承AbstractDistributeOnceElasticJobListener类
         */
        private String distributedListener;

        /**
         * 最后一个作业执行前的执行方法的超时时间，单位：毫秒
         */
        private long startedTimeoutMilliseconds;

        /**
         * 最后一个作业执行后的执行方法的超时时间，单位：毫秒
         */
        private long completedTimeoutMilliseconds;
}
