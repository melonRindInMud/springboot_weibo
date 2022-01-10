package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.support.SupportService;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 负责处理点赞的请求的控制器
@Controller
public class SupportController {

    // 自动注入点赞的业务逻辑类对象
    @Autowired
    private SupportService s;

    // (在可以点赞的基础上，增加点赞)
    // 要求上并没有取消点赞，就不写取消点赞了，但是仍然可以查询不能重复点赞
    @ResponseBody
    @PostMapping("/addSupport")
    void addSupport(@RequestParam(name="ArticleId")String articleId, @RequestParam(name="UserName")String userName) {
        s.addSupport(userName, articleId);
    }

    // 获取一个用户的所有点赞信息
    @RequestMapping("/support/user/*")
    String showUserSupports(Model model, HttpServletRequest request) {
        // 先获取userName(在url中)
        String url = request.getRequestURL().toString();
        String[] result = url.split("/");
        String userName = result[result.length - 1];   // 用户名

        // 根据用户名查询 数据库中的点赞，然后添加到model中 返回前端
        List<Support> list = s.getUserSupports(userName);
        model.addAttribute("info", list);
        return "Support";
    }
}
