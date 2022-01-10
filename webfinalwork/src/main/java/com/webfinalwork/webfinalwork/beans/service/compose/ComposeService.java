package com.webfinalwork.webfinalwork.beans.service.compose;

import com.webfinalwork.webfinalwork.beans.repository.filePersistence.ArticlePersistence;
import com.webfinalwork.webfinalwork.beans.repository.jpa.AboutRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.ArticleInfoRepository;
import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.About;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Article;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import com.webfinalwork.webfinalwork.data.transmit.compose.ComposeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ComposeService {

    @Autowired
    private ArticleInfoRepository ar;    // JPA 操作

    @Autowired
    private UserInfoRepository ur;

    @Autowired
    private ArticlePersistence p;       //  文章内容写入文件操作

    @Autowired
    private AboutRepository abr;        // about 相关

    // 检测（起始没有什么可以检测的）并处理提交的创作文章请求
    public String composeCheck(ComposeInfo info, MultipartFile img, HttpServletRequest request) {

        // 提取相关数据
        String userName = (String)request.getSession().getAttribute("login");    // 从 session 中 提取登录的用户的用户名
        String articleContext = info.getInfo();                                    // 提取文章内容
        String imgName = "";
        if (!img.isEmpty())                                                        // 图片
            imgName = img.getOriginalFilename();                                   // 获取图片名称

        // 提取向数据库中的持久化信息
        Article newArticle = new Article(userName, info.getTitle(), articleContext, imgName);

        // 提取图像文件
        String folderPath = ArticlePersistence.savePath + "/articles/" + String.valueOf(newArticle.getArticleId());
        // 存储图像和文章内容的目录路径

        // 将文章信息写入数据库
        addArticle(userName, newArticle);

        // 创建文件夹
        File folder = new File(folderPath);
        folder.mkdir();

        // 识别文章中所有的@用户的部分 将所有存在的@用户信息 存储到数据库，并且在文章内容中增加超链接
        articleContext = parseAboutInArticleContent(articleContext, newArticle);

        // 将文章内容存入目标文件中
        p.saveArticleContent(articleContext, folderPath);

        // 存储图片
        if (!img.isEmpty())
            p.saveImg(img, folderPath);
        return "ok";                                                               // 目前阶段返回值没什么用，但是估计后面有用
    }

    // 将文章存入文章数据库，同时给创作者的创作数 + 1
    private void addArticle(String userName, Article info) {
        ar.save(info);             // 将文章信息存储 文章数据库
        List<UserInfoAll> user = ur.findByUserName(userName);     // 查找用户
        user.get(0).setArticles(user.get(0).getArticles() + 1);   // 增加文章数
        ur.save(user.get(0));
    }

    // 分析文章内容中所有合理的@ 为其添加<a> 超链接内容 并且存入数据库 最后返回处理完毕的文章内容
    // 合理的@ 格式  @[0-9_a-zA-Z\u4e00-\u9fa5]{6,}  -> 由此再分析(上限不要设定，因为可能会导致截断)
    private String parseAboutInArticleContent(String content, Article article) {
        String regex = "@[0-9_a-zA-Z\u4e00-\u9fa5]{6,}";               // 相关正则表达式
        Pattern pattern = Pattern.compile(regex);                      // 编译正则表达式
        Matcher matcher = pattern.matcher(content);                    // 准备用编译好的正则表达式匹配目标字符串
        String result = content;

        while(matcher.find()) {  // 发现匹配
            String tempMatch = matcher.group(0);

            String tempUser = tempMatch.substring(1);
            List<UserInfoAll> list = ur.findByUserName(tempUser);
            if (0 != list.size()) {                                                                       // 能找到
                // 将 该部分变为 <a> 元素 + 链接地址
                String labelForm = "<a href=\"/userPublicInfo/" + tempUser + "\">" + tempMatch + "</a>";
                result = result.replaceFirst(tempMatch, labelForm);

                // 将 @ 信息存入数据库中
                About about = new About(article.getOwnerName(), article.getArticleId(), tempUser);
                abr.save(about);
            }
        }
        return result;
    }
}
