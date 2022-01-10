package com.webfinalwork.webfinalwork.data.persistance.dataBase;


import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// 点赞类
@Data
@Entity
@Table(name="Praise")
@NoArgsConstructor
public class Support {

    @Id
    private long praiseId;      // 主键 id 用点赞时刻作为id

    @Basic
    private String userName;    // 点赞的人的名称

    @Basic
    private long articleId;     // 点赞的文章id

    @Basic
    private String date;        // 点赞时间（字符串表示形式）

    public Support(String userName, long articleId) {
        this.praiseId = new Date().getTime();
        this.userName = userName;
        this.articleId = articleId;
        this.date = DateFormTransformer.TimeToDate(this.praiseId);
    }

}
