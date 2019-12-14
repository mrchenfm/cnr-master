package com.ecut.cnr.framework.config.elasticjob.autoconfig;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname ZookeeperAutoConfig
 * @Description
 * @Date 2019/12/14 17:37
 * @Create by fangming_chen
 */
@Configuration
@ConditionalOnProperty("elasticjob.zookeeper.server-lists")
public class ZookeeperAutoConfig {
    @Autowired
    private ZookeeperProperties zookeeperProperties;

    /**
     * <h2>zookeeper注册中心<h2>
     * @return {@link CoordinatorRegistryCenter}
     * @author InedibleMushroom
     * @date 2019/10/29
     **/
    @Bean(initMethod = "init")
    public  CoordinatorRegistryCenter coordinatorRegistryCenter(){
        String serverList = zookeeperProperties.getServerLists();
        String nameSpace = zookeeperProperties.getNameSpace();
        ZookeeperConfiguration zc = new ZookeeperConfiguration(serverList, nameSpace);

        CoordinatorRegistryCenter crc = new ZookeeperRegistryCenter(zc);

        // 注册中心初始化 使用initMethod = "init" 代替
        //crc.init();

        return crc;
    }
}
