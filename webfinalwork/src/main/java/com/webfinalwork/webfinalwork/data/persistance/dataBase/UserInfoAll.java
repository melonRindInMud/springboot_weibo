package com.webfinalwork.webfinalwork.data.persistance.dataBase;

import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
// import java.util.Date;

// 用户所有信息 用于存放在数据库中(持久化)
@Data                     // 自动生成getter 和 setter函数 以及 ToString 函数
@Entity                   // 持久化实体类
@Table(name="UserInfo")   // 持久化目标数据库table
public class UserInfoAll {

    @Id
    private final String userName;   // 用户名 主键

    @Basic
    private String passWord;         // 密码

    @Basic
    private final String logEmail;   // 注册邮箱

    @Basic
    private int focus;               // 关注人数

    @Basic
    private int beFocus;             // 被关注数

    @Basic
    private int bePraised;           // 被赞数

    @Basic
    private int articles;            // 文章数

    @Basic
    private int comments;            // 评论数

    @Basic
    private String registerDate;     // 注册日期字符串表示形式，自动生成

    // 默认构造函数 -- 用户信息起始并没有默认构造的，所以这种情况其实不会出现，但是持久化中的实体类要求必须有无参构造函数
    public UserInfoAll() {
        this.userName = null;
        this.passWord = null;
        this.logEmail = null;
    }

    // 只有 用户名， 密码， 邮箱的构造函数（正常的创建用户）
    public UserInfoAll(String userName, String passWord, String logEmail) {
        this.userName = userName;
        this.passWord = passWord;
        this.logEmail = logEmail;
        this.focus = 0;
        this.beFocus = 0;
        this.bePraised = 0;
        this.articles = 0;
        this.comments = 0;
        this.registerDate = DateFormTransformer.TimeToDate(new Date().getTime());
    }
}

/* 弃用方案 因为时间太紧张
@Data                     // 自动生成getter 和 setter函数 以及 ToString 函数
@Entity                   // 持久化实体类
@Table(name="UserInfo")   // 持久化目标数据库table
@AllArgsConstructor       //
public class UserInfoAll {

    @Id
    private final int id;                  // 用户id

    @Basic
    private String userName;         // 用户名

    @Basic
    private String passWord;         // 密码

    @Basic
    private String signature;        // 签名

    @Basic
    private String profilePhotoURL;  // 头像图片URL（相对于服务器端，服务器端的存储有本地映射）

    @Basic
    private int experience;          // 经验值

    @Basic
    private int golden;              // 金币

    @Basic
    private long vipToMoment;        // 会员到期时间（毫秒表示）

    @Basic
    private final long registerMoment;          // 注册时间

    @Basic
    private long lastLogMoment;                 // 上次登录时间

    @Basic
    private long lastAlterNameMoment;           // 上次修改用户名的时间

    @Basic
    private long lastAlterPassWordMoment;       // 上次修改密码的时间

    // 默认构造函数
    public UserInfoAll() {
        long current = new Date().getTime();   // 获取现在时间的毫秒表示法
        this.id = -1;
        this.userName = null;
        this.passWord = null;
        this.signature = null;
        this.profilePhotoURL = null;
        this.experience = 0;
        this.golden = 0;
        this.vipToMoment = 0;
        this.registerMoment = current;
    }
}
*/