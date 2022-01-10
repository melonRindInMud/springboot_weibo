package com.webfinalwork.webfinalwork.data.transmit.article;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import lombok.Data;

import java.util.List;

// 用于记录一个页面上最多5个文章的简介信息 同时说明这是第n页 以及文章总数
@Data
public class ArticleInfoPage {

    private int page;     // 第n页

    private int total;    // 总计

    List<Article> info;   // 内容

    // 构造函数
    public ArticleInfoPage(int page, int total, List<Article> info) {
        this.page = page;
        this.total = total;
        this.info = info;
    }
}
