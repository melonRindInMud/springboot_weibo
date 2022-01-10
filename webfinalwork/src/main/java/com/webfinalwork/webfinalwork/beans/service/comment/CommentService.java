package com.webfinalwork.webfinalwork.beans.service.comment;


import com.webfinalwork.webfinalwork.beans.repository.jpa.ArticleInfoRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.CommentRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Comment;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 评论业务逻辑处理
@Service
public class CommentService {

    // 自动注入 负责评论持久化的jpaRepository
    @Autowired
    CommentRepository cr;

    // 对应文章的 评论数需要+1 所以还需要 文章的持久化操作
    @Autowired
    ArticleInfoRepository ar;

    // 评论者的评论数 + 1
    @Autowired
    UserInfoRepository ur;

    // 将从控制器接收的评论信息 持久化到数据库中
    public void recvComment(String userName, String articleId, String comment) {
        // 将评论信息存储到数据库中
        Long id = Long.parseLong(articleId);
        Comment info = new Comment(userName, id, comment);
        cr.save(info);

        // 将对应文章的评论数 + 1
        List<Article> list = ar.findByArticleId(id);
        Article article = list.get(0);
        article.setComments(article.getComments() + 1);
        ar.save(article);

        // 将评论者的评论数 + 1
        UserInfoAll commentOwner = ur.findByUserName(userName).get(0);
        commentOwner.setComments(commentOwner.getComments() + 1);
        ur.save(commentOwner);
    }
}
