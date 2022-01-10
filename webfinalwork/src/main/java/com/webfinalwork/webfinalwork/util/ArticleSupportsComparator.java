package com.webfinalwork.webfinalwork.util;

import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;

import java.util.Comparator;

public class ArticleSupportsComparator implements Comparator {
    //排序比较函数
    public int compare(Object o1, Object o2) {
        Article a1 = (Article)o1;
        Article a2 = (Article)o2;

        int supports1 = a1.getSupports();
        int supports2 = a2.getSupports();

        if (supports1 > supports2)         // 降序排列
            return -1;
        else if (supports1 == supports2)
            return 0;
        else
            return 1;
    }
}
