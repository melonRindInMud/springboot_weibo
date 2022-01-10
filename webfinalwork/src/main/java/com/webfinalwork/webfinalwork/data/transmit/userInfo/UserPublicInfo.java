package com.webfinalwork.webfinalwork.data.transmit.userInfo;


import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import lombok.Data;

// 用于将指定用户的可见信息传递给前端
@Data
public class UserPublicInfo {

    private String userName;      // 用户名

    private String registerDate;  // 注册日期

    private int focus;            // 关注用户数

    private int beFocus;          // 被关注数

    private int comments;         // 评论数

    private int articles;         // 文章数

    private int bePraised;        // 被赞数

    // 构造函数 直接从数据库中取出用户所有信息然后将其中部分用于初始化这个类
    public UserPublicInfo(UserInfoAll user) {
        this.userName = user.getUserName();
        this.registerDate = user.getRegisterDate();
        this.focus = user.getFocus();
        this.beFocus = user.getBeFocus();
        this.comments = user.getComments();
        this.articles = user.getArticles();
        this.bePraised = user.getBePraised();
    }

}
