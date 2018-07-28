package com.pilicon.user.service.impl;

import com.pilicon.user.dao.UserInfoDao;
import com.pilicon.user.entity.UserInfo;
import com.pilicon.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByOpenId(String openid) {
        UserInfo userInfo = userInfoDao.findByOpenId(openid);
        return userInfo;
    }
}
