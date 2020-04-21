package com.ecut.cnr.view.fastdfs;

import lombok.Data;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerGroup;

import java.net.InetSocketAddress;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/3 09:22
 * @Description:
 */
@Data
public class FastDFSFile {
    /** 连接池默认最小连接数 */
    private long minPoolSize ;
    /** 连接池默认最大连接数 */
    private long maxPoolSize;
    /** 默认等待时间（单位：秒） */
    private long waitTimes;


    /**
     * private
     */
    private int connectTimeout;
    private int networkTimeout;
    private String charset;
    private int trackerHttpPort;
    private boolean antiStealToken;
    private String secretKey;
    private String trackerServer;

    public void init(){
        ClientGlobal.setG_anti_steal_token(this.antiStealToken);
        ClientGlobal.setG_connect_timeout(this.getConnectTimeout());
        ClientGlobal.setG_network_timeout(this.getNetworkTimeout());
        ClientGlobal.setG_charset(this.getCharset());
        ClientGlobal.setG_tracker_http_port(this.getTrackerHttpPort());
        ClientGlobal.setG_secret_key(this.getSecretKey());

        String[] szTrackerServers = this.getTrackerServer().split(",");
        if (szTrackerServers == null) {
            throw new BusinessException("fastDfs服务器地址未配置");
        } else {
            InetSocketAddress[] trackerServers = new InetSocketAddress[szTrackerServers.length];
            for(int i = 0; i < szTrackerServers.length; ++i) {
                String[] parts = szTrackerServers[i].split("\\:", 2);
                if (parts.length != 2) {
                    throw new BusinessException(parts+"配置不正正确（fastDfs）");
                }
                trackerServers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            }
            TrackerGroup trackerGroup = new TrackerGroup(trackerServers);
            ClientGlobal.setG_tracker_group(trackerGroup);
        }
    }

}
