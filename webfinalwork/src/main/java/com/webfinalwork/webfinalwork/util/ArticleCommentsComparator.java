package com.webfinalwork.webfinalwork.util;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import lombok.NoArgsConstructor;

import java.util.Comparator;

// 根据 评论数排序文章的比较器类
@NoArgsConstructor
public class ArticleCommentsComparator implements Comparator {
    //排序比较函数
    public int compare(Object o1, Object o2) {
        Article a1 = (Article)o1;
        Article a2 = (Article)o2;

        int comments1 = a1.getComments();
        int comments2 = a2.getComments();

        if (comments1 > comments2)         // 降序排列
            return -1;
        else if (comments1 == comments2)
            return 0;
        else
            return 1;
    }
}
