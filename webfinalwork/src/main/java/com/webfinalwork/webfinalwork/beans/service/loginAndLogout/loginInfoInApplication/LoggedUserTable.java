package com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication;

import java.util.Vector;

// application 作用域维护的，已经登录的用户名称表
public class LoggedUserTable {

    private Vector<String> info;                          // 已登录用户表

    public void loginUser(String name) {                  // 添加新登录的用户
        info.addElement(name);
        System.out.println("用户" + name + "登录了");       // 后台测试使用
    }

    public void logoutUser(String name) {                // 登出登录的用户
        int index = info.indexOf(name);                  // 查找该用户
        if (-1 == index) {                               // 没找到 按正常逻辑来讲，这不可能，如果出现就说明有问题
            System.out.println("用户登出时发现了未登录的用户名名称，请检查代码逻辑");
        }
        else {                                            // 这是正常的逻辑
            info.remove(name);                            // 删除元素
            System.out.println("用户" + name + "登出了");   // 后台测试使用
        }
    }

    public boolean isLogin(String name) {                 // 判断该用户是否已经登录了
        int index = info.indexOf(name);
        if (-1 == index)
            return false;
        else
            return true;
    }

    // 默认构造函数
    public LoggedUserTable() {
        this.info = new Vector<String>();
    }
}
