package com.webfinalwork.webfinalwork.beans.repository.jpa;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 点赞信息持久化接口
// 点赞必须登录(但是评论可以不登录)
@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    List<Support> findByArticleIdAndUserName(long articleId, String userName);  // 通过点赞人的名称和被评论文章查找点赞
    List<Support> findByUserName(String userName);                              // 通过点赞人的名称获取其所有点赞信息
}
