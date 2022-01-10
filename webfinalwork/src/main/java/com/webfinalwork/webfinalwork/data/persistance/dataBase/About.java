package com.webfinalwork.webfinalwork.data.persistance.dataBase;

import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// @ 信息持久化类
// 由于时间原因，现在只支持在文章中@用户
@Data
@Entity
@Table(name="about")
@NoArgsConstructor
public class About {

    @Id
    private long aboutId;       // 主键 @ 信息Id (以创建时间的Time表示法作为id)

    @Basic
    private String date;        // 日期的字符串表示法

    @Basic
    private String ownerName;   // @ 发起者的用户名

    @Basic
    private long articleId;     // @ 用户出现在的文章的ID

    @Basic
    private String targetUser;  // 被提及者的用户名

    // 构造函数
    public About(String ownerName, long articleId, String targetUser) {
        this.aboutId = new Date().getTime();
        this.date = DateFormTransformer.TimeToDate(this.aboutId);
        this.ownerName = ownerName;
        this.articleId = articleId;
        this.targetUser = targetUser;
    }

}
