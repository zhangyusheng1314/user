package com.zys.user.enums;

import lombok.Getter;

@Getter
public enum ResultEnums {
    LOGIN_FAIL(1,"登录失败"),
    LOGIN_SUCCESS(0,"登录成功"),
    ROLE_ERROR(2,"角色错误"),
    LOGIN_REPEAT(3,"登录中");
    private Integer code;

    private String message;

    ResultEnums(Integer code,String message) {
        this.code = code;
        this.message =message;
    }
}
