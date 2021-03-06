package com.dmsg.channel;

import com.dmsg.cache.HostCache;
import com.dmsg.data.UserDetail;
import com.dmsg.server.DmsgServerContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cjl on 2016/8/11.
 */
public class RemoteHostChannelManager {

    private static Map<String, ChannelHandlerContext> sessions = new ConcurrentHashMap<String, ChannelHandlerContext>();
    private static Map<ChannelId, String> relations = new ConcurrentHashMap<ChannelId, String>();
    private static RemoteHostChannelManager instance;
    private static HostCache hostCache;

    public static RemoteHostChannelManager getInstance(DmsgServerContext dmsgServerContext) {
        if (instance == null) {
            instance = new RemoteHostChannelManager();
            hostCache = dmsgServerContext.getHostCache();
        }
        return instance;
    }

    private RemoteHostChannelManager() {

    }

    // 增加用户与连接的上下文映射
    public void addContext(String name, ChannelHandlerContext ctx) {
        UserDetail userDetail = new UserDetail();
        userDetail.setLastTime(System.currentTimeMillis());
        userDetail.setUserName(name);
        userDetail.setStatus(1);
        userDetail.setLoginHost(DmsgServerContext.getServerContext().getHostDetail());

        synchronized (sessions) {
            sessions.put(name, ctx);
            relations.put(ctx.channel().id(), name);
        }
    }

    public ChannelHandlerContext getContext(String name){
        return sessions.get(name);
    }

    public void removeContext(String name){
        sessions.remove(name);
    }

    public boolean isAvailable(String name){
        return sessions.containsKey(name) && (sessions.get(name) != null);
    }

    public synchronized Set<String> getAll(){
        return sessions.keySet();
    }

    public synchronized Collection<ChannelHandlerContext> getAllClient(){
        return sessions.values();
    }

    public void removeContext(ChannelHandlerContext ctx){
        String name = relations.get(ctx.toString());
        if(name != null){
            sessions.remove(name);
            relations.remove(ctx.toString());
        }
        hostCache.remove(name);
    }


    public int staticClients(){
        return relations.size();
    }

    public String findUserByChannelId(ChannelId channelId) {
        return relations.get(channelId);
    }
}
