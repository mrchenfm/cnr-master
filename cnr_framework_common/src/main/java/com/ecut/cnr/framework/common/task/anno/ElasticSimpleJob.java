package com.ecut.cnr.framework.common.task.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ElasticSimpleJob
 * @Description
 * @Date 2019/12/14 17:29
 * @Create by fangming_chen
 */
// 目标   ElementType.TYPE 注解打在类上
@Target(ElementType.TYPE)
// 级别   RetentionPolicy.RUNTIME 在运行时使用
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ElasticSimpleJob {

    String jobName() default "";

    String cron() default "";

    int shardingTotalCount() default 1;

    boolean overwrite() default false;

    /**
     * 前置后置任务分布式监听实现类，需继承AbstractDistributeOnceElasticJobListener类
     */
    String listener() default "";

    /**
     * 前置后置任务分布式监听实现类，需继承AbstractDistributeOnceElasticJobListener类
     */
    String distributedListener() default "";

    /**
     * 最后一个作业执行前的执行方法的超时时间，单位：毫秒
     */
    long startedTimeoutMilliseconds() default Long.MAX_VALUE;

    /**
     * 最后一个作业执行后的执行方法的超时时间，单位：毫秒
     */
    long completedTimeoutMilliseconds() default Long.MAX_VALUE;
	/* --------------------job:distributed-listener命名空间属性详细说明 end-------------------- */

}
