package com.webfinalwork.webfinalwork.beans.repository.jpa;


import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 文章信息数据库JPA操作接口
@Repository
public interface ArticleInfoRepository extends JpaRepository<Article, Long> {
    List<Article> findByArticleId(long id);  // 查
}
