package com.webfinalwork.webfinalwork.beans.repository.jpa;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Focus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 关注信息持久化JPA类
// 关注信息 支持 查询 增加 和 删除
@Repository

public interface FocusRepository extends JpaRepository<Focus, Long> {
    List<Focus> findByOwnerName(String ownerName);                                   // 查询一个用户的所有关注信息
    List<Focus> findByTargetName(String targetName);                                 // 查询一个用户的所有被关注信息
    List<Focus> findByOwnerNameAndTargetName(String ownerName, String targetName);   // 精确查询

    // 删除操作必须需要这个注解 否则会报错
    @Transactional
    void deleteByOwnerNameAndTargetName(String ownerName, String targetName);        // 精确删除
}
