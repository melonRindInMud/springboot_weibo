package com.webfinalwork.webfinalwork.beans.service.loginAndLogout;

import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.LoggedUserTable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

// 登出业务逻辑类
@Service
public class LogOutService {

    public void LogOut(String userName, HttpServletRequest request) {
        // 获取登录用户信息表
        LoggedUserTable loggedInfo = (LoggedUserTable)request.getServletContext().getAttribute("loggedUsers");
        loggedInfo.logoutUser(userName);                      // 登出用户
        request.getSession().removeAttribute("login");      // 将session的登录标识给取消掉
    }
}
