package com.qk.chat.tcp.utils;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionSocketHolder {
    private static final Map<String, NioSocketChannel> CHANNELMAP = new ConcurrentHashMap<>();

    public static void put(String userId, NioSocketChannel value) {
        CHANNELMAP.put(userId, value);
    }

    public static NioSocketChannel get(String userId) {
        return CHANNELMAP.get(userId);
    }
}
