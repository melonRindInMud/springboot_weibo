package com.webfinalwork.webfinalwork.data.transmit.login;

import lombok.Data;

import java.io.Serializable;

// 用于登录时， 浏览器向服务器提交的数据类 最开始也由服务器随Model传递给前端
@Data
public class LoginInfo implements Serializable {

    private String userName;     // 用户名

    private String passWord;     // 密码

}
