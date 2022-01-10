package com.webfinalwork.webfinalwork.beans.service.loginAndLogout;

import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.LoggedUserTable;
import com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication.UserLogFailedTable;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import com.webfinalwork.webfinalwork.data.transmit.login.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 登录业务逻辑类
@Service
public class LoginService {

    @Autowired
    private UserInfoRepository r;  // 用户信息数据库JPA

    // 处理登录请求的具体逻辑
    public String checkLogin(LoginInfo info, HttpServletRequest request) {
        // 首先判断 用户名是否存在
        List<UserInfoAll> result = r.findByUserName(info.getUserName());

        if (0 == result.size())   // 不存在
            return "用户名不存在";  // 只需要return 不需要额外操作
        else {                    // 用户名存在
            //获取application 对象 及 用户登录失败信息表
            ServletContext application = request.getServletContext();
            UserLogFailedTable failureInfo = (UserLogFailedTable) application.getAttribute("failureInfo");
            long lockDuration = failureInfo.isUserLocked(info.getUserName());   // 获取锁定时长

            if (0 != lockDuration)  // 被锁定
                return "用户被锁定, 剩余" + String.valueOf(lockDuration / 1000.0) + "秒";  // 直接返回，不需要额外操作
            else {                  // 没被锁定
                if (!result.get(0).getPassWord().equals(info.getPassWord())) { // 密码不正确
                    failureInfo.Update(info.getUserName());              // 增加一次错误尝试
                    return "密码错误";
                }
                else {                                             // 密码也正确
                    // 拿出登录信息表
                    LoggedUserTable loggedInfo = (LoggedUserTable) application.getAttribute("loggedUsers");
                    if (loggedInfo.isLogin(info.getUserName()))    // 登录了
                        return "该用户已经登录";
                    else {                                         // 到这一步才算登录成功
                        loggedInfo.loginUser(info.getUserName());  // 在application 中 增加该用户的登录信息
                        request.getSession().setAttribute("login", info.getUserName());  // 在session 中添加登录标识
                        return "ok";
                    }
                }
            }
        }
    }
}
