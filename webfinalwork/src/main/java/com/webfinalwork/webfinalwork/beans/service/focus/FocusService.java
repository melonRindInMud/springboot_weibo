package com.webfinalwork.webfinalwork.beans.service.focus;


import com.webfinalwork.webfinalwork.beans.repository.jpa.FocusRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Focus;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 关注和 取消关注的相关操作的业务处理类
@Service
public class FocusService {

    // 自动注入关注信息JPA
    @Autowired
    FocusRepository fr;

    // 自动注入用户信息JPA (更新被关注数 和 关注数的问题)
    @Autowired
    UserInfoRepository ur;

    // 添加关注信息
    public void addFocus(String loginUser, String targetUser) {
        // 将关注信息存入数据库
        Focus focus = new Focus(loginUser, targetUser);
        fr.save(focus);

        // 关注者 关注数 + 1
        UserInfoAll owner = ur.findByUserName(loginUser).get(0);
        owner.setFocus(owner.getFocus() + 1);
        ur.save(owner);

        // 被关注者 被关注数 + 1
        UserInfoAll target = ur.findByUserName(targetUser).get(0);
        target.setBeFocus(target.getBeFocus() + 1);
        ur.save(target);
    }

    // 取消关注信息
    public void deleteFocus(String loginUser, String targetUser) {
        // 从数据库中 删除关注信息
        fr.deleteByOwnerNameAndTargetName(loginUser, targetUser);

        // 关注者 关注数 + 1
        UserInfoAll owner = ur.findByUserName(loginUser).get(0);
        owner.setFocus(owner.getFocus() - 1);
        ur.save(owner);

        // 被关注者 被关注数 + 1
        UserInfoAll target = ur.findByUserName(targetUser).get(0);
        target.setBeFocus(target.getBeFocus() - 1);
        ur.save(target);
    }
}
