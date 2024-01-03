package com.qk.chat.codec.proto;

import lombok.Data;

@Data
public class MessageHeader {
    private Integer command;

    private Integer version;

    private Integer clientType;

    private Integer parseType;

    private Integer appId;

    private Integer imeiLen;

    private Integer bodyLen;

    private String imei;
}
