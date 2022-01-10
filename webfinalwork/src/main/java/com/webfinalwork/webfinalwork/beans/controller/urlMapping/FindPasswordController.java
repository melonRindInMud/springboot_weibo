package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.findPassword.FindPasswordService;
import com.webfinalwork.webfinalwork.data.transmit.findPassword.FindPasswordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

// 用于找回密码相关的请求的controller
@Controller
public class FindPasswordController {

    // 自动注入 相关业务逻辑类对象
    @Autowired
    private FindPasswordService fs;

    // 请求找回密码的页面
    @RequestMapping("/findPassword")
    String findPassword(Model model) {
        FindPasswordInfo info = new FindPasswordInfo();   // 该对象用于绑定给前端 让前端填写
        model.addAttribute("info", info);
        return "FindPassword";
    }

    // 请求重置密码的页面
    @RequestMapping("/resetPassword/*")
    String resetPassword(Model model, HttpServletRequest request) {
        // 提取url中最后一段(用户名)
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String strName = info[info.length - 1];                // 取最后一个  -- 目前拆分结果没有问题

        model.addAttribute("user", strName);
        return "ReSetPassword";
    }

    // 检测找回密码的信息(邮箱是否正确)
    @ResponseBody
    @RequestMapping("/checkFindPassword")
    String checkFindPassword(FindPasswordInfo info) {
        return fs.checkFindPassword(info) ? "ok" : "no";
    }

    // 重置密码
    @ResponseBody
    @RequestMapping("/reset")
    void reset(@RequestParam(name="user")String user, @RequestParam(name="password")String password) {
        fs.resetPassword(user, password);
    }
}
