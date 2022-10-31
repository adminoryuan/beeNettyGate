package com.bee.Cache;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yh
 * @date 2022/9/30 下午11:36
 */
public class WebConnectCache {
    /**
     * 关联apikey和channel
     */
    private static ConcurrentHashMap<String, Channel> connMap=
                                                        new ConcurrentHashMap<>();

    public static void put(String key,Channel channel){
        connMap.put(key,channel);
    }
    public static Channel get(String key){
        return connMap.get(key);
    }
    public static boolean isExist(String key){
        return connMap.containsKey(key);
    }

    public static boolean remove(Channel channel){
        return connMap.remove(channel)!=null;
    }
}
