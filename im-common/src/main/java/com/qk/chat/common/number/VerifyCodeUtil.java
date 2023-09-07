package com.qk.chat.common.number;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * {@code @ClassName} VerifyCodeUtil
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/8/28 16:44
 */
public class VerifyCodeUtil {
    
    /**
     * 随机生成指定长度字符串验证码
     *
     * @param length 验证码长度
     */
    public static String generateVerifyCode(int length) {
        String strRange = "1234567890";
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            char ch = strRange.charAt((new Random()).nextInt(strRange.length()));
            strBuilder.append(ch);
        }
        return strBuilder.toString();
    }
}
