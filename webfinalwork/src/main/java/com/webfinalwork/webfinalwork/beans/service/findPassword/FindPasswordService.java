package com.webfinalwork.webfinalwork.beans.service.findPassword;

import com.webfinalwork.webfinalwork.beans.repository.jpa.UserInfoRepository;
import com.webfinalwork.webfinalwork.data.persistance.dataBase.UserInfoAll;
import com.webfinalwork.webfinalwork.data.transmit.findPassword.FindPasswordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// 找回密码相关部分的业务逻辑处理类
@Service
public class FindPasswordService {

    // 自动注入
    @Autowired
    private JavaMailSender mailSender;

    // 自动注入用户信息类JPA
    @Autowired
    private UserInfoRepository ur;

    // 使用配置文件中的值 为 数据成员进行初始化
    @Value("${spring.mail.username}")
    private String emailFrom;

    // 用于检查 找回密码信息中的邮箱是否正确
    public boolean checkFindPassword(FindPasswordInfo info) {
        // 由于动态检测用户名是否存在，所以能提交上来的都是存在的用户名
        System.out.println(info);
        UserInfoAll user = ur.findByUserName(info.getUserName()).get(0);

        // 检查邮箱是不是一致 (提交上来的邮箱就算是空串 也不是null 所以 可以不用equals)
        if (user.getLogEmail().equals(info.getEmail())) {
            sendResetLinkMail(info);
            System.out.println("邮件已发送");
            return true;
        }
        else
            return false;
    }

    // 用于修改密码
    public void resetPassword(String user, String password) {
        UserInfoAll userInfo = ur.findByUserName(user).get(0);
        userInfo.setPassWord(password);
        ur.save(userInfo);
    }

    // 发送相关邮件
    public void sendResetLinkMail(FindPasswordInfo info) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(this.emailFrom);                              // 邮件发送人
            messageHelper.setTo(info.getEmail());                               // 邮件接收人

            message.setSubject("用户" + info.getUserName() + "重置密码");         // 邮件主题

            // qq 邮箱自带屏蔽 href 似乎 用js试试
            String dest = "localhost:8080/resetPassword/" + info.getUserName();
            String content = "<a>" + dest + "</a>";

            messageHelper.setText(content, true);                          // 邮件内容，html格式

            mailSender.send(message);                                           // 发送
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
