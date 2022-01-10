package com.webfinalwork.webfinalwork.beans.service.support;

import com.webfinalwork.webfinalwork.beans.repository.jpa.ArticleInfoRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.SupportRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Support;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 点赞的业务逻辑处理类
@Service
public class SupportService {

    // 自动注入 点赞信息持久化的 JPA
    @Autowired
    SupportRepository sr;

    // 自动注入 文章信息持久化的JPA
    @Autowired
    ArticleInfoRepository ar;

    // 自动注入 用户信息持久化的JPA
    @Autowired
    UserInfoRepository ur;

    // 在能增加点赞的基础上增加点赞
    public void addSupport(String userName, String articleId) {
        Long id = Long.parseLong(articleId);
        // 将点赞数据持久化
        Support support = new Support(userName, id);
        sr.save(support);

        // 相应的文章的点赞数 + 1
        List<Article> result = ar.findByArticleId(id);
        Article article = result.get(0);
        article.setSupports(article.getSupports() + 1);
        ar.save(article);

        // 文章所有者的被赞数 + 1
        UserInfoAll owner = ur.findByUserName(article.getOwnerName()).get(0);
        owner.setBePraised(owner.getBePraised() + 1);
        ur.save(owner);
    }

    // 获取一个用户的所有点赞记录
    public List<Support> getUserSupports(String userName) {
        return sr.findByUserName(userName);
    }
}
