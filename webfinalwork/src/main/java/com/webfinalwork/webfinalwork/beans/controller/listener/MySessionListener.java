package com.webfinalwork.webfinalwork.beans.controller.listener;

import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.LoggedUserTable;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// session 监听器 用于监听session 的创建和销毁
// 该类是 一个bean 其从 CSR 中 仍然属于controller 控制器层的 bean
@Controller
public class MySessionListener implements HttpSessionListener {

    // 有新session 创建时的事件响应函数
    @Override
    public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // 设置最大非活跃时间 单位秒 同时还需要设置session cookie 的有效时间（设置为同等长度） 才能生效
        httpSessionEvent.getSession().setMaxInactiveInterval(600);
    }

    // 有session 销毁时的事件响应函数
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String userName = (String)httpSessionEvent.getSession().getAttribute("login");
        if (null != userName) {             // 有登录用户
            LoggedUserTable loginInfo =     // 获取登录用户信息表
            (LoggedUserTable) httpSessionEvent.getSession().getServletContext().getAttribute("loggedUsers");
            loginInfo.logoutUser(userName); // 从信息表中删除该用户
        }
    }
}
