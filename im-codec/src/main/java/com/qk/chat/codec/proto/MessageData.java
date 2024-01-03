package com.qk.chat.codec.proto;

import lombok.Data;

@Data
public class MessageData {
    private MessageHeader messageHeader;

    private Object messagePack;
}
