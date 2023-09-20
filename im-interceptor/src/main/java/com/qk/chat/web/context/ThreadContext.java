package com.qk.chat.web.context;


public class ThreadContext {
    private static InheritableThreadLocal<LoginUserInfo> loginTokenThreadLocal = new InheritableThreadLocal();

    public ThreadContext() {
    }

    public static void bindLoginToken(LoginUserInfo loginToken) {
        loginTokenThreadLocal.set(loginToken);
    }

    public static LoginUserInfo getLoginToken() {
        return (LoginUserInfo)loginTokenThreadLocal.get();
    }
    

    public static void clearToken() {
        loginTokenThreadLocal.remove();
    }
    
}