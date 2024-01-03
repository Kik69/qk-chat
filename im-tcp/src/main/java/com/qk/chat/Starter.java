package com.qk.chat;

import com.qk.chat.tcp.server.LimServer;

public class Starter {
    public static void main(String[] args) {
        new LimServer(9000);
    }
}