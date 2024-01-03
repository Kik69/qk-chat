package com.qk.chat.common.enmu;

import com.qk.chat.common.command.Command;

public enum SystemCommand implements Command {
    LOGIN(0x2328);

    public int command;

    SystemCommand(int command) {
        this.command = command;
    }

    @Override
    public int getCommand() {
        return this.command;
    }
}
