package com.qk.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qk.chat.codec.proto.MessageData;
import com.qk.chat.codec.proto.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 消息解码器
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 请求头 + 版本 + clientType + 消息解析类型 + appId + imei长度 + bodyLen + imei号 + body
        if (byteBuf.readableBytes() < 28) {
            return;
        }
        // 获取 command
        int command = byteBuf.readInt();
        // 获取版本号
        int version = byteBuf.readInt();
        // 获取客户端类型
        int clientType = byteBuf.readInt();
        // 获取消息解析类型
        int parseType = byteBuf.readInt();
        // 获取 appId
        int appId = byteBuf.readInt();
        // 获取 imei 长度
        int imeiLen = byteBuf.readInt();
        // 获取 body 长度
        int bodyLen = byteBuf.readInt();

        if (byteBuf.readableBytes() < imeiLen + bodyLen) {
            byteBuf.resetReaderIndex();
            return;
        }

        // 获取 imei 号
        byte[] imeiBytes = new byte[imeiLen];
        byteBuf.readBytes(imeiBytes);
        String imei = new String(imeiBytes);
        // 获取 body
        byte[] bodyBytes = new byte[bodyLen];
        byteBuf.readBytes(bodyBytes);


        // 封装成 Message 对象
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setCommand(command);
        messageHeader.setVersion(version);
        messageHeader.setClientType(clientType);
        messageHeader.setParseType(parseType);
        messageHeader.setAppId(appId);
        messageHeader.setImeiLen(imeiLen);
        messageHeader.setBodyLen(bodyLen);
        messageHeader.setImei(imei);

        MessageData messageData = new MessageData();
        messageData.setMessageHeader(messageHeader);

        if (parseType == 0x0){
            // json
            String body = new String(bodyBytes);
            JSONObject msgBody = JSON.parseObject(body, JSONObject.class);
            messageData.setMessagePack(msgBody);
        }
        byteBuf.markReaderIndex();
        list.add(messageData);
    }
}