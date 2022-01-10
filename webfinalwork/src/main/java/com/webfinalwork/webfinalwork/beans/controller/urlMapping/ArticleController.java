package com.webfinalwork.webfinalwork.beans.controller.urlMapping;


import com.webfinalwork.webfinalwork.beans.service.article.ArticleService;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Comment;
import com.webfinalwork.webfinalwork.data.transmit.article.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 用于响应请求某一篇文章的url
@Controller
public class ArticleController {

    // 注入获取文章的业务逻辑类
    @Autowired
    private ArticleService s;

    // 请求相关文章
    @RequestMapping("/article/*")
    String getArticle(HttpServletRequest request, Model model) {
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String strId = info[info.length - 1];                // 取最后一个  -- 目前拆分结果没有问题

        long id = -1;
        id = Long.parseLong(strId);                          // 将路径中最后一段(id)翻译成long int

        ArticleInfo articleInfo = s.getArticleInfo(id);      // 获取对应id 的文章的完整信息
        model.addAttribute("article", articleInfo);        // 添加到model中 以便传回前端

        List<Comment> comments = s.getArticleComments(id);   // 获取对应id 的文章的所有评论信息
        model.addAttribute("comments", comments);          // 同上

        if (null == request.getSession().getAttribute("login"))                             // 没登录
            model.addAttribute("support", "noLog");
        else {                                                                                 //  登录了
            if (s.getSupportValid((String)request.getSession().getAttribute("login"), id))  // 查不到评价记录 可以评价
                model.addAttribute("support", "ok");
            else                                                                               // 查得到评价记录 不能评价
                model.addAttribute("support", "no");
        }
        return "Article";
    }
}
