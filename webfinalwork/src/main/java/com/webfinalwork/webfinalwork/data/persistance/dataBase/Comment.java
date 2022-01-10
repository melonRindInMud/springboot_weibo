package com.webfinalwork.webfinalwork.data.persistance.dataBase;

import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// 评论
@Data
@Entity
@Table(name="comment")
@NoArgsConstructor
public class Comment {

    @Id
    private long commentId;     // id 用评论时间做id

    @Basic
    private String date;        // 评论时间 用id 转换格式以后得到

    @Basic
    private String ownerName;   // 评论者的名字

    @Basic
    private long targetArticle; // 所评论的文章的id

    @Basic
    private String info;        // 评论的内容

    // 构造函数
    public Comment(String ownerName, long targetArticle, String info) {
        this.commentId = new Date().getTime();
        this.ownerName = ownerName;
        this.targetArticle = targetArticle;
        this.info = info;
        this.date = DateFormTransformer.TimeToDate(this.commentId);
    }
}
