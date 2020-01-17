package com.zys.user.respository;

import com.zys.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findUserInfoByOpenid(String openid);
}
