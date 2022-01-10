package com.webfinalwork.webfinalwork.data.transmit.findPassword;

import lombok.Data;

// 找回密码信息的类
@Data
public class FindPasswordInfo {

    private String userName;    // 提交的用户名信息

    private String email;       // 提交的邮箱信息

    // 构造函数
    public FindPasswordInfo() {
        this.userName = "";
        this.email = "";
    }
}
