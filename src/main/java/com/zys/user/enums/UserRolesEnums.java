package com.zys.user.enums;

import lombok.Getter;

@Getter
public enum UserRolesEnums {
    BUYER(1,"买家"),
    SELLER(2,"卖家")
    ;
    private Integer code;

    private String message;

    UserRolesEnums(Integer code, String message) {
        this.code = code;
        this.message =message;
    }
}
