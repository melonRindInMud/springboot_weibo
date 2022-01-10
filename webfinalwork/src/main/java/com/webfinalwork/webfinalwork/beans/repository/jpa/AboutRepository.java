package com.webfinalwork.webfinalwork.beans.repository.jpa;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @ 信息持久化的JPA
@Repository
public interface AboutRepository extends JpaRepository<About, Long> {
    List<About> findByTargetUser(String targetUser);  // 查找一个用户所有被提及到记录
}
