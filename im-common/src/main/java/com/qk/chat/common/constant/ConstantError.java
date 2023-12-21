package com.qk.chat.common.constant;

/**
 * {@code @ClassName} ConstantError
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/9/16 10:40
 */
public enum ConstantError implements ErrorSupport{
    FIND_USER_IS_EXISTS(false,GlobalModule.GLOBAL,"01","查无此人，请确认邮箱是否正确!"),
    
    FIND_EXIST_USER_RELA(false,GlobalModule.GLOBAL,"01","存在好友关系!"),
    NOT_EXIST_USER_RELA(false,GlobalModule.GLOBAL,"01","不存在好友关系!"),
    NOT_EXIST_FRIEND_RELA(false,GlobalModule.GLOBAL,"01","not exists friend relation!"),
    NOT_ADD_ONESELF_FRIEND(false,GlobalModule.GLOBAL,"01","不能添加自己!"),
    
    NOT_IS_REPEAT_APPLY(false,GlobalModule.GLOBAL,"01","请不要重复申请!"),
    
    MAILBOX_EXISTS_REGISTER(false,GlobalModule.GLOBAL,"01","邮箱已注册!"),
    
    VERIFY_PASS_FAULT(false,GlobalModule.GLOBAL,"01","确认密码错误!"),
    
    VERIFY_CODE_FAULT(false,GlobalModule.GLOBAL,"01","验证码错误!"),
    
    SYSTEM_ERROR(false,GlobalModule.GLOBAL,"01","系统内部异常，请联系管理员处理!"),
    USER_ERROR(false,GlobalModule.GLOBAL,"01","用户名或密码错误!"),
    USER_ACTIVE_FORBIDDEN(false,GlobalModule.GLOBAL,"01","用户已禁用，请联系管理员处理!"),
    DATA_ERROR(false,GlobalModule.GLOBAL,"01","数据异常!"),
    TARGET_BLACK_EXISTS(false,GlobalModule.GLOBAL,"02","您已被对方拉入黑名单!"),
    ALREADY_BLACK_EXISTS(false,GlobalModule.GLOBAL,"02","该好友已经被你拉黑!"),
    NOT_BLACK_SELF(false,GlobalModule.GLOBAL,"02","不能拉黑自己!"),
    EXISTS_GROUP(false,GlobalModule.GLOBAL,"03","已经存在该分组!"),
    NOT_EXISTS_GROUP(false,GlobalModule.GLOBAL,"03","分组不存在!")
    
    ;
    private boolean system;
    private ModuleSupport module;
    private String code;
    private String message;

    ConstantError(boolean system, ModuleSupport module, String code, String message) {
        this.system = system;
        this.message = message;
        this.module = module;
        this.code = (system ? 0 : 1) + module.code() + code;
    }

    @Override
    public boolean system() {
        return this.system;
    }

    @Override
    public ModuleSupport module() {
        return this.module;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
