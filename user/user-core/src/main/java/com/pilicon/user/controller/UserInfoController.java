package com.pilicon.user.controller;

import com.pilicon.user.constants.CookieConstant;
import com.pilicon.user.constants.RedisConstant;
import com.pilicon.user.dto.UserInfoDto;
import com.pilicon.user.entity.UserInfo;
import com.pilicon.user.enums.RoleEnum;
import com.pilicon.user.exception.LoginException;
import com.pilicon.user.service.UserInfoService;
import com.pilicon.user.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.pilicon.user.constants.CookieConstant.expire;

@RestController
@RequestMapping("login")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @RequestMapping(value = "buyer",method = RequestMethod.GET)
    public UserInfoDto buyer(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response)throws Exception{
        // 判断是否已经登录 如果没有叫tooken的cookie 说明没有登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return null;
        }
        //1.openId 和数据库里里的数据是否匹配
        UserInfo userInfo = userInfoService.findByOpenId(openid);
        if (userInfo == null){
            throw new LoginException();
        }
        //2.判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()){
            throw new LoginException();
        }
        //3.redis操作
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),openid,expire,TimeUnit.SECONDS);

        //4.cookie里设置openid=abc
        CookieUtil.set(response,CookieConstant.OPENID,openid,CookieConstant.expire);

        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo,userInfoDto);
        return userInfoDto;
    }

    /**
     * 卖家登录
     * @param openId
     * @param response
     * @return
     */
    @RequestMapping(value = "seller",method = RequestMethod.GET)
    public UserInfoDto seller(@RequestParam("openid") String openid,HttpServletRequest request, HttpServletResponse response)throws Exception{
        // 判断是否已经登录 如果没有叫tooken的cookie 说明没有登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null && StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))){
            return null;
        }

        //1.openId 和数据库里里的数据是否匹配
        UserInfo userInfo = userInfoService.findByOpenId(openid);
        if (userInfo == null){
            throw new LoginException();
        }
        //2.判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()){
            throw new LoginException();
        }
        //3.    //todo redis操作 不是很会redis
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),openid,expire,TimeUnit.SECONDS);

        //4.cookie里设置openid=abc
        CookieUtil.set(response,CookieConstant.TOKEN,openid,CookieConstant.expire);

        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo,userInfoDto);
        return userInfoDto;
    }
}
