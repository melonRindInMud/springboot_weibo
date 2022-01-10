package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.LogOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 登出用户的相关请求的相应controller
@Controller
public class LogOutController {

    // 自动注入登出用户的业务逻辑类对象
    @Autowired
    private LogOutService s;

    // 登出需要点击相关按钮退出 不能直接请求相关url退出
    // 返回值 暂时不需要
    @ResponseBody
    @PostMapping("/logOut")
    void logOut(HttpServletRequest request) {
        String currentUser = (String)request.getSession().getAttribute("login");
        s.LogOut(currentUser, request);
    }

}
