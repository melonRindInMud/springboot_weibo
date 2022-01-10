package com.webfinalwork.webfinalwork.beans.service.userInfo;

import com.webfinalwork.webfinalwork.beans.repository.jpa.FocusRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Focus;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import com.webfinalwork.webfinalwork.data.transmit.userInfo.UserPublicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 获取其他用户的可展示信息的业务逻辑处理类
@Service
public class UserInfoService {

    // 自动注入 用户信息持久化JPA类对象
    @Autowired
    private UserInfoRepository ur;

    // 自动注入 关注信息持久化JPA类对象
    @Autowired
    private FocusRepository fr;

    // 获取相关用户的公开信息
    public UserPublicInfo getUserPublicInfo(String userName) {
        UserInfoAll user = ur.findByUserName(userName).get(0);
        return new UserPublicInfo(user);
    }

    // 判断该用户是否可以关注目标用户
    public boolean isFocusValid(String loginUser, String targetUser) {
        List<Focus> result = fr.findByOwnerNameAndTargetName(loginUser, targetUser);
        /// 不存在相关关注信息 可以关注 否则不能关注
        return 0 == result.size();
    }
}
