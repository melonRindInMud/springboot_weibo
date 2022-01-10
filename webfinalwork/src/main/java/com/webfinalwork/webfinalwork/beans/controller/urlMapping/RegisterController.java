package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.register.RegisterService;
import com.webfinalwork.webfinalwork.data.transmit.register.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired   // 自动注入 注册业务逻辑处理类
    private RegisterService s;

    // 注册 URL 通过 get 方式请求注册页面
    // 该方法会将一个空白的提交注册信息类对象通过model 传递给前端，以便前端填写
    @GetMapping("/register")
    public static String showRegisterPage(Model model, RegisterInfo info) {
        model.addAttribute("info", info);
        return "Register";
    }

    // 处理提交注册的请求 (ajax) 进行处理并且返回结果
    // @ResponseBody 注解 表示该方法不返回View 视图模型，而是返回json xml text 等数据文本
    // 由于这个参数在前端绑定过，所以 可以直接接收（依赖注入）
    @ResponseBody
    @PostMapping("/checkRegister")
    public boolean checkRegister(@Autowired RegisterInfo info) {
        return s.checkRegister(info);
    }

    // 处理输入用户名时动态提交的用户名查重
    // 如果用户名重复，则返回1 否则返回0
    // @ResponseBody 注解 表示该方法不返回View 视图模型，而是返回json xml text 等数据文本
    // 注解 @RequestParam 表示 该参数对应 请求中同名的参数（在name 中设置对应的名字）
    @ResponseBody
    @PostMapping("/checkUserName")
    public int checkUserName(@RequestParam(name="userName") String name) {
        return s.checkUserName(name);
    }
}
