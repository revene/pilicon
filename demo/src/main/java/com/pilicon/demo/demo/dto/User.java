package com.pilicon.demo.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {

    private String userName;

    //直接不返回给前端
    @JsonIgnore
    private String password;

    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据。2）出参时，Date类型的数据自动转换为14位的字符串返回出去。
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date date;

    //如果为空 则不返回
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
