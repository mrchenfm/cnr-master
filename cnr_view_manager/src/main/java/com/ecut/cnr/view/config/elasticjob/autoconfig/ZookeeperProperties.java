package com.ecut.cnr.view.config.elasticjob.autoconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname ZookeeperProperties
 * @Description
 * @Date 2019/12/14 17:42
 * @Create by fangming_chen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "elasticjob.zookeeper")
public class ZookeeperProperties {
    /**
     * application.yml 中根据get和set方法获得属性和注入属性
     * elasticjob:
     *   zookeeper:
     *     server-list: 106.52.39.199:2181
     *     name-space: springboot-elasticjob
     */

    /** zookeeper 地址列表 */
    private String serverLists;

    /** zookeeper 命名空间 */
    private String nameSpace;
}
