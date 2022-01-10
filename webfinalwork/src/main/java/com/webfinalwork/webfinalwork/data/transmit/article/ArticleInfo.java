package com.webfinalwork.webfinalwork.data.transmit.article;

import com.webfinalwork.webfinalwork.beans.repository.filePersistence.ArticlePersistence;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import lombok.Data;

import java.io.*;

// 用于从后端发往前端的文章全部信息（id 标题 作者 全部内容 图片 点赞数 评论数） 和在数据库中的信息稍微有所不同
// 内容可以通过id 来寻找  图片需要提供对应的url
// 创作日期 最后选择在后端进行翻译(int -> 年月日格式)，然后传递给前端，这样可以省去在前端翻译
@Data
public class ArticleInfo {

    private long id;           // 文章id (唯一)

    private String title;      // 标题

    private String ownerName;  // 创作者的用户名

    private String imgUrl;     // 图片的url

    private String content;    // 文章内容

    private String date;       // 日期格式为 xxxx年.xx月.xx日 xx:xx

    private int supports;      // 获赞数

    private int comments;      // 评论数

    // 构造函数 在找到相关文章的情况下 可以由 JPA 的 entity 类 直接构造
    public ArticleInfo(Article article) throws IOException {
        this.id = article.getArticleId();
        this.title = article.getTitle();
        this.ownerName = article.getOwnerName();
        this.imgUrl = article.getImgUrl();
        this.supports = article.getSupports();
        this.comments = article.getComments();

        // 日期的字符串表示
        this.date = article.getDate();

        String urlLocation = ArticlePersistence.savePath + "/articles/" + String.valueOf(this.id) + "/";
        String httpLocation = "/static/articles/" + String.valueOf(this.id) + "/";

        // 图片URL
        this.imgUrl = httpLocation + article.getImgUrl();

        // 从文件中读取文章信息
        String filePath = urlLocation + "content.txt";
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;                                                            // 用来保存每行读取的内容
        StringBuffer buffer = new StringBuffer();                               // 用来记录文件中的全部内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();                                               // 读取第一行
        while (line != null) {                                                  // 如果 line 为空说明读完了
            buffer.append(line);                                                // 将读到的内容添加到 buffer 中
            buffer.append("\n");                                                // 添加换行符
            line = reader.readLine();                                           // 读取下一行
        }
        reader.close();
        is.close();
        this.content = buffer.toString();
    }

    // 默认构造函数 在找不到相关文章的情况下创建一个空白信息
    // 接收端接收的时候以id < 0 区分
    public ArticleInfo() {
        this.id = -1;
        this.title = null;
        this.ownerName = null;
        this.imgUrl = null;
        this.supports = -1;
        this.comments = -1;
    }
}
