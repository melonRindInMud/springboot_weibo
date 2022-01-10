package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.LoginService;
import com.webfinalwork.webfinalwork.data.transmit.login.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 用于登录URL的响应
@Controller
public class LoginController {

    // 业务逻辑层bean 注入
    @Autowired
    private LoginService s;

    // 登录标识仍然放在session中 因为有多个用户，所以仅仅是标识符是不够的，我们额外设立了类
    // 请求登录页面
    @RequestMapping("/login")
    public String login(HttpServletRequest request, LoginInfo info, Model model) {
        if (null == request.getSession().getAttribute("login")) { // 如果没有登录用户信息
            model.addAttribute("loginInfo", info);
            return "Login";
        }
        else
            return "Navigation";
    }

    // 对登录请求进行判断 登录请求是ajax 类型请求
    // 返回ok 代表登录成功，返回其他代表失败，同时该返回值代表提示信息
    @ResponseBody
    @PostMapping("/loginCheck")
    public String loginCheck(@Autowired LoginInfo info, HttpServletRequest request) {
        return s.checkLogin(info, request);
    }
}
