package com.ecut.cnr.framework.config.elasticjob.autoconfig;

import org.springframework.core.annotation.AliasFor;
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

    @AliasFor("cron")
    String value() default "";

    @AliasFor("value")
    String cron() default "";


    int shardingTotalCount() default 1;

    String shardingItemParameters() default "";

    String jobParameter() default "";

    String dataSource() default "";

    String description() default "";

    boolean disabled() default false;

    boolean overwrite() default false;

    boolean failover() default true;

    boolean monitorExecution() default true;

}
