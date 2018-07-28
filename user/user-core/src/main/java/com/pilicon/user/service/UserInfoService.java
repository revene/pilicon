package com.pilicon.user.service;

import com.pilicon.user.entity.UserInfo;

public interface UserInfoService {

    UserInfo findByOpenId(String openId);
}
