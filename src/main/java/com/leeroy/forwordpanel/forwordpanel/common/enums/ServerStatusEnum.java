package com.leeroy.forwordpanel.forwordpanel.common.enums;

/**
 * @program: forwordpanel
 * @description: 服务器状态
 * @author: liruifeng
 * @created: 2020/10/13 16:55
 */
public enum  ServerStatusEnum {
    INIT(1, "初始化中"), CONNECT_FAIL(2, "连接失败"), ONLINE(3, "在线");

    ServerStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
