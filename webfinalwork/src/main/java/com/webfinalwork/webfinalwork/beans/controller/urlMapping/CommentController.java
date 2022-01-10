package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 负责接收评论请求的控制器
@Controller
public class CommentController {

    // 自动注入一个负责评论业务逻辑处理的类对象
    @Autowired
    CommentService s;

    // 从前端获取一个评论
    @ResponseBody
    @PostMapping("/checkComment")
    void recvComment(@RequestParam(name="ArticleId")String articleId, @RequestParam(name="UserName")String userName,
                     @RequestParam(name="Comment")String comment) {
        // 评论信息现在可以被正确接收 调用service 类 将其存入数据库 不需要返回信息？ 可能需要返回一个评论成功字样（但是那个不是重点）
        s.recvComment(userName, articleId, comment);
    }
}
