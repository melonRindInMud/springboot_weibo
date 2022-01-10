package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.focus.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 关注用户 取消关注 相关的请求的相应 Controller
@Controller
public class FocusController {

    // 自动注入相关的业务逻辑类对象
    @Autowired
    FocusService fs;

    // 关注用户 (ajax 请求)
    @ResponseBody
    @PostMapping("/addFocus")
    void addFocus(@RequestParam(name="targetUser")String targetUser, HttpServletRequest request) {
        // 从session 中 获取当前登录的用户的用户名
        String loginUser = (String)request.getSession().getAttribute("login");
        fs.addFocus(loginUser, targetUser);
    }

    // 取消关注用户 (ajax 请求)
    @ResponseBody
    @PostMapping("/deleteFocus")
    void deleteFocus(@RequestParam(name="targetUser")String targetUser, HttpServletRequest request) {
        // 套路 同上， 只不过变成删除
        String loginUser = (String)request.getSession().getAttribute("login");
        fs.deleteFocus(loginUser, targetUser);
    }
}
