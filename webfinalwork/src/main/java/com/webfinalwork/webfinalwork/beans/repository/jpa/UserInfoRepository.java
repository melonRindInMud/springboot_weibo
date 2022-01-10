package com.webfinalwork.webfinalwork.beans.repository.jpa;


import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JPA 操作接口 用于 实现 UserInfo 类的 持久化 和 增删改查
// 继承 JpaRepository 接口 即可 增删改查通过 方法名来确定
// 基类中有 增的 同时我们不需要删除的，所以只用查和改就行
// 用户名是主键，所以查和改的检索依据都是用户名
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoAll, Long> {

    List<UserInfoAll> findByUserName(String userName); // 查
    // 该和增都可以通过 save 方法实现
}
