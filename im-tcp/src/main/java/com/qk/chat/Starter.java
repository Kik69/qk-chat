package com.qk.chat;


import com.qk.chat.tcp.server.NettyServer;

public class Starter {
    public static void main(String[] args) {
        new NettyServer(9000);
    }
}