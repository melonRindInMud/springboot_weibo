package com.webfinalwork.webfinalwork.data.persistance.dataBase;

import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// 用于存储文章的信息 (不包括文章内容 文章内容在其ID对应的txt文件中)
// 每一个文章在服务器上创建一个同名目录，目录下包含一个txt 文件（内容） 和 若干张图片
//
@Data
@Entity
@Table(name="article")
@NoArgsConstructor
public class Article {

    @Id
    private long articleId;          // 主键 文章id 用创建时间作为主键

    @Basic
    private String title;            // 文章标题

    @Basic
    private String brief;            // 简介（前30个字符）

    @Basic
    private String imgUrl;           // 文章附属图片资源的url

    @Basic
    private String ownerName;        // 发布者的 id

    @Basic
    private int supports;            // 点赞数

    @Basic
    private int comments;            // 评论数

    @Basic
    private String date;             // 时间的字符串表示法

    public Article(String ownerName, String title, String contentInfo, String imgUrl) {   // 发布文章的时候用这个构造函数
        this.ownerName = ownerName;
        this.title = title;
        this.brief = contentInfo.substring(0, 30);
        this.articleId = new Date().getTime();
        this.imgUrl = imgUrl;
        this.comments = 0;
        this.supports = 0;
        this.date = DateFormTransformer.TimeToDate(this.articleId);
    }

}
