package com.webfinalwork.webfinalwork.beans.controller.listener;

import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.LoggedUserTable;
import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.UserLogFailedTable;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// application 监听器
@Controller
public class MyApplicationListener implements ServletContextListener {

    // application 初始化时的操作
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();               // 获取application 范围对象
        application.setAttribute("loggedUsers", new LoggedUserTable());     // 增加登录用户的信息表
        application.setAttribute("failureInfo", new UserLogFailedTable());  // 增加用户尝试错误信息表
        System.out.println("应用启动");        // 后端测试使用
    }

    // application 销毁时的操作
    @Override
    public  void  contextDestroyed(ServletContextEvent event){
        System.out.println("应用关闭");        // 后端测试使用
    }
}
