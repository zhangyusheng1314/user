package com.zys.user.controller;

import com.zys.user.constant.CookieConstant;
import com.zys.user.domain.UserInfo;
import com.zys.user.enums.ResultEnums;
import com.zys.user.enums.UserRolesEnums;
import com.zys.user.respository.UserInfoRepository;
import com.zys.user.utils.CookieUtil;
import com.zys.user.utils.ResultVOUtils;
import com.zys.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/userLogin")
public class UserController {

    private @Autowired
    UserInfoRepository userInfoRepository;
    private @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/buyer")
    private ResultVO buyer(@RequestParam String openid, HttpServletResponse response){
        UserInfo userInfo = userInfoRepository.findUserInfoByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtils.error(ResultEnums.LOGIN_FAIL);
        }
        if (userInfo.getRole()!= UserRolesEnums.BUYER.getCode()) {
            return ResultVOUtils.error(ResultEnums.ROLE_ERROR);
        }
        //设置cookie 买家openid放入cookie
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.EXPIRE);
        return ResultVOUtils.success(ResultEnums.LOGIN_SUCCESS);
    }

    @RequestMapping("/seller")
    private ResultVO seller(@RequestParam String openid, HttpServletResponse response,
                            HttpServletRequest request){
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        //判断是否登录 如果已登录的话不在创建cookie和往redis里放值
        if (cookie != null
                && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue())))){
            return ResultVOUtils.success(ResultEnums.LOGIN_REPEAT);
        }
        UserInfo userInfo = userInfoRepository.findUserInfoByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtils.error(ResultEnums.LOGIN_FAIL);
        }
        if (userInfo.getRole()!= UserRolesEnums.SELLER.getCode()) {
            return ResultVOUtils.error(ResultEnums.ROLE_ERROR);
        }
        String token = UUID.randomUUID().toString();
        //卖家token存入redis
        stringRedisTemplate.opsForValue().set(
                String.format("token_%s", token),
                openid,CookieConstant.EXPIRE, TimeUnit.SECONDS);
        //卖家token存入cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.EXPIRE);
        return ResultVOUtils.success(ResultEnums.LOGIN_SUCCESS);
    }
}
