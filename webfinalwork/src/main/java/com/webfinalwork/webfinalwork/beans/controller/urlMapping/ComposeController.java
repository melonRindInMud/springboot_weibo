package com.webfinalwork.webfinalwork.beans.controller.urlMapping;

import com.webfinalwork.webfinalwork.beans.service.compose.ComposeService;
import com.webfinalwork.webfinalwork.data.transmit.compose.ComposeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

// 用于创作部分响应请求的处理
@Controller
public class ComposeController {

    @Autowired
    private ComposeService s;      // 创作文章业务逻辑类

    // 创作页面 （没有登录也可以过去，但是不能提交）
    @RequestMapping("/compose")
    public String compose(HttpServletRequest request, ComposeInfo info, Model model) {
        model.addAttribute("info", info);               // 增加提交信息给前端，让前端填写后返回给后端
        return "Compose";
    }

    // 创作提交页面
    @PostMapping("/composeCheck")
    public String composeCheck(ComposeInfo info, @RequestParam(name="img")MultipartFile img,
                               HttpServletRequest request) {
        s.composeCheck(info, img, request);
        return "ComposeSuccess";
    }
}
