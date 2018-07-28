package com.pilicon.user.dao;

import com.pilicon.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,String> {

    @Query("select p from UserInfo p where p.openid = :openid")
    public UserInfo findByOpenId(@Param("openid")String openid);
}
