package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.userInfo.UserInfoService;
import com.webfinalwork.webfinalwork.data.transmit.userInfo.UserPublicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 查询和获得用户信息的请求的响应 controller
@Controller
public class UserInfoController {

    // 自动注入 相关的业务逻辑处理类
    @Autowired
    UserInfoService us;

    // 返回指定用户的可见信息
    @RequestMapping("/userPublicInfo/*")
    String showUserPublicInfo(HttpServletRequest request, Model model) {
        // 解析 地址中的 用户名
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String userName = info[info.length - 1];             // 取最后一个  -- 目前拆分结果没有问题

        // 将相关用户的公开信息 存入 model 中
        UserPublicInfo userInfo = us.getUserPublicInfo(userName);
        model.addAttribute("info", userInfo);

        // 判断当前登录状态(未登录/登录用户已经关注过这个用户/登录用户还没有关注过这个用户)是否可以关注该用户
        HttpSession session = request.getSession();
        if (null == session.getAttribute("login"))                       // 未登录 设置状态
            model.addAttribute("flag", "unLogin");
        else {                                                             // 登录了
            String loginUser = (String)session.getAttribute("login");    // 获取登录用户的用户名
            if (us.isFocusValid(loginUser, userName))                      // 可以关注
                model.addAttribute("flag", "ok");
            else                                                           // 不能关注 可以取消关注
                model.addAttribute("flag", "no");
        }

        return "UserPublicInfo";
    }
}
