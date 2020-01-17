package com.zys.user.service;

import com.zys.user.domain.UserInfo;

public interface UserInfoService {

    UserInfo findUserInfoByOpenid(String openid);
}
