package com.qk.chat.tcp.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.qk.chat.codec.pack.LoginPack;
import com.qk.chat.codec.proto.MessageData;
import com.qk.chat.common.enmu.SystemCommand;
import com.qk.chat.tcp.utils.SessionSocketHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServerHandler extends SimpleChannelInboundHandler<MessageData> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageData messageData) throws Exception {
        Integer command = messageData.getMessageHeader().getCommand();
        if (command == SystemCommand.LOGIN.getCommand()) {
            LoginPack loginPack = JSON.parseObject(JSONObject.toJSONString(messageData.getMessagePack()),new TypeReference<LoginPack>(){}.getType());
            ctx.channel().attr(AttributeKey.valueOf("userId")).set(loginPack.getUserId());
            // 将channel 存起来
            SessionSocketHolder.put(loginPack.getUserId(), (NioSocketChannel) ctx.channel());
        }
    }
}
