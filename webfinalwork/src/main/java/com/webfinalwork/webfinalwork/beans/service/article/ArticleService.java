package com.webfinalwork.webfinalwork.beans.service.article;

import com.webfinalwork.webfinalwork.beans.repository.jpa.ArticleInfoRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.CommentRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.SupportRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Comment;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Support;
import com.webfinalwork.webfinalwork.data.transmit.article.ArticleInfo;
import com.webfinalwork.webfinalwork.data.transmit.article.ArticleInfoPage;
import com.webfinalwork.webfinalwork.util.ArticleCommentsComparator;
import com.webfinalwork.webfinalwork.util.ArticleSupportsComparator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 获取文章信息的业务逻辑类
// 目前只返回文章信息 之后还需要返回评论信息 （多一个JPA bean）
@Service
public class ArticleService {

    // 自动注入 获取文章信息的JPA
    @Autowired
    private ArticleInfoRepository r;

    // 自动注入 获取对文章的评论的JPA
    @Autowired
    private CommentRepository r2;

    // 自动注入 获取对文章的点赞的JPA
    @Autowired
    private SupportRepository r3;

    // 获取相关文章的除文章评论内容以外的所有信息
    @SneakyThrows
    public ArticleInfo getArticleInfo(long id) {
        List<Article> result = r.findByArticleId(id);              // 根据给出的id 查询目标文章
        if (0 != result.size()) {
            return new ArticleInfo(result.get(0));
        }
        else                                                       // 没有相关文章
            return new ArticleInfo();
    }

    // 获取相关文章的文章评论(所有, 以list形式存储)
    public List<Comment> getArticleComments(long id) {
        List<Comment> result = r2.findByTargetArticle(id);         // 数据库中是按时间正序存储的，这里需要逆序
        Collections.reverse(result);                               // 将列表逆序
        return result;
    }

    // 获取当前文章是否可以被点赞的信息
    public boolean getSupportValid(String userName, long id) {
        List<Support> result = r3.findByArticleIdAndUserName(id, userName);
        return 0 == result.size();                                 // 有评论返回false 没有评论返回false
    }

    // 获取第 n 个 页面信息 (不含排序)
    public ArticleInfoPage getPage(int page, List<Article> all) {
        int length = all.size();            // 获取长度
        int begin = 5 * (page - 1);                // 起始下标

        // 提取第 5 * page + 1 -> 5 * page + 5 个 下标分别 要 -1
        List<Article> result = new ArrayList<>();
        for (int i = begin; i < length && i < begin + 5; i++)
            result.add(all.get(i));

        return new ArticleInfoPage(page, length, result);
    }

    // 获取根据发布时间倒序排序的页面信息
    public ArticleInfoPage getPageByDate(int page) {
        // 查找全部 并且按日期逆序排序
        List<Article> info = r.findAll();
        Collections.reverse(info);           // 日期的存储本来就是从旧到新的，因此不需要排序 只需要逆序即可

        // 排好序直接调用函数即可
        return getPage(page, info);
    }

    // 获取根据点赞数倒序排序的页面信息
    public ArticleInfoPage getPageBySupport(int page) {
        // 查找全部 并且按点赞数逆序排序
        List<Article> info = r.findAll();
        ArticleSupportsComparator comparator = new ArticleSupportsComparator();
        info.sort(comparator);

        return getPage(page, info);
    }

    // 获取根据评论数倒序排序的页面信息
    public ArticleInfoPage getPageByComment(int page) {
        // 查找全部 并且按评论数逆序排序
        List<Article> info = r.findAll();
        ArticleCommentsComparator comparator = new ArticleCommentsComparator();
        info.sort(comparator);

        return getPage(page, info);
    }
}
