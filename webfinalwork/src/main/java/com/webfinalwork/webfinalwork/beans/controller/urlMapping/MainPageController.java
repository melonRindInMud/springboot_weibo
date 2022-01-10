package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.article.ArticleService;
import com.webfinalwork.webfinalwork.data.transmit.article.ArticleInfoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// 负责对主界面的请求等的响应
@Controller
public class MainPageController {

    // 注入获取文章的业务逻辑类
    @Autowired
    private ArticleService s;

    // 直接请求主页默认为请求主页的按日期排序的第一页
    @RequestMapping("/main")
    String mainPage() {
        return "redirect:/main/date/1";
    }

    // 请求一个页面上的文章简介 (按时间排序) 最后一级路径是第n页
    @RequestMapping("/main/date/*")
    String getPageByDate(HttpServletRequest request, Model model) {
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String strPage = info[info.length - 1];              // 取最后一个  -- 目前拆分结果没有问题
        Integer page = Integer.valueOf(strPage);

        ArticleInfoPage result = s.getPageByDate(page);
        model.addAttribute("info", result);
        return "Main";
    }

    // 请求一个页面上的文章简介 (按点赞多少排序) 最后一级路径是第n页
    @RequestMapping("/main/support/*")
    String getPageBySupport(HttpServletRequest request, Model model) {
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String strPage = info[info.length - 1];              // 取最后一个  -- 目前拆分结果没有问题
        Integer page = Integer.valueOf(strPage);

        ArticleInfoPage result = s.getPageBySupport(page);
        model.addAttribute("info", result);
        return "Main";
    }

    // 请求一个页面上的文章简介 (按评论多少排序) 最后一级路径是第n页
    @RequestMapping("/main/comment/*")
    String getPageByComment(HttpServletRequest request, Model model) {
        String url = request.getRequestURL().toString();     // 获取请求路径
        String[] info = url.split("/");                // 拆分请求路径
        String strPage = info[info.length - 1];              // 取最后一个  -- 目前拆分结果没有问题
        Integer page = Integer.valueOf(strPage);

        ArticleInfoPage result = s.getPageByComment(page);
        model.addAttribute("info", result);
        return "Main";
    }

    @RequestMapping("/navigation")
    public String navigation() {
        return "Navigation";
    }
}
