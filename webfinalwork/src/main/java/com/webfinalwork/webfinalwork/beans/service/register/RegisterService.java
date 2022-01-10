package com.webfinalwork.webfinalwork.beans.service.register;

import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import com.webfinalwork.webfinalwork.data.transmit.register.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 注册业务逻辑类
@Service
public class RegisterService {

    @Autowired
    private UserInfoRepository r;  // 用户信息数据库JPA

    // 检查注册用户信息是否可用，如果可用，加入数据库
    public boolean checkRegister(RegisterInfo info) {
        List<UserInfoAll> result = r.findByUserName(info.getUserName());  // 查重
        if (0 == result.size()) {            // 没有
            r.save(new UserInfoAll(info.getUserName(), info.getPassWord(), info.getLogEmail()));  // 将信息存入数据库
            return true;
        }
        else                  // 重复了
            return false;
    }

    // 用于动态检查 用户名是否可用
    public int checkUserName(String name) {
        System.out.println(name);
        List<UserInfoAll> result = r.findByUserName(name);  // 查重
        if (0 == result.size())                             // 没重复
            return 0;
        else  // 重复了
            return 1;
    }
}
