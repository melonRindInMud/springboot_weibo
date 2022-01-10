package com.webfinalwork.webfinalwork.data.transmit.register;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;   // 序列化接口

//  注册时由前端提交的信息
@Data                          // 自动生成getter 和 setter函数 以及 ToString 函数
@AllArgsConstructor            // 生成一个含有所有数据成员同类型形参的构造函数
public class RegisterInfo implements Serializable {   // 实现序列化接口（自动序列化接口的方法有缺省定义） 用于在http协议上的传递

    private String userName;    // 提交的用户名

    private String passWord;    // 提交的密码

    private String logEmail;    // 提交的邮箱

    public RegisterInfo() {            // 无参构造函数，用于在后端创造一个空白对象让前端填写
        this.userName = "";
        this.passWord = "";
        this.logEmail = "";
    }
}
