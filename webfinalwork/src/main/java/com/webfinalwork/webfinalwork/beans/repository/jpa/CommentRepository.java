package com.webfinalwork.webfinalwork.beans.repository.jpa;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 评论 主要有 增和查(第一版)
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentId(long id);                                //  通过id 查询
    List<Comment> findByTargetArticle(long id);                            //  通过所属文章的id 查询
    List<Comment> findByOwnerName(String user);                            //  通过评论用户查找
    List<Comment> findByOwnerNameAndTargetArticle(String user, long id);   //  通过评论用户和文章id查询
}
