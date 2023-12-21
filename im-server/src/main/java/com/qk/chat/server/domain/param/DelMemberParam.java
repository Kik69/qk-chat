package com.qk.chat.server.domain.param;

import lombok.Data;

@Data
public class DelMemberParam {
    private String groupId;

    private String toId;
}
