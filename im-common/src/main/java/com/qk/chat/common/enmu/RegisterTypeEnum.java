package com.qk.chat.common.enmu;

/**
 * {@code @ClassName} RegisterTypeEnum
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/11/2 10:48
 */
public enum RegisterTypeEnum {
    IM_ZERO(0),
    IM_ONE(1),
    IM_TWO(2),
    IM_THREE(3);

    private int code;

    RegisterTypeEnum(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
