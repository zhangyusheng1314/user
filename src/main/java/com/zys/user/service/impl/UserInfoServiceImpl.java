package com.zys.user.service.impl;

import com.zys.user.domain.UserInfo;
import com.zys.user.respository.UserInfoRepository;
import com.zys.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private @Autowired
    UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findUserInfoByOpenid(String openid) {
        return userInfoRepository.findUserInfoByOpenid(openid);
    }
}
