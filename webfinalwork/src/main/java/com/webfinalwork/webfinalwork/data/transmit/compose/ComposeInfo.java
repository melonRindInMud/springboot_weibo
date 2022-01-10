package com.webfinalwork.webfinalwork.data.transmit.compose;

import lombok.Data;

@Data
public class ComposeInfo {

    // 用户名从session 中 获取

    private String title;      // 标题

    private String info;       // 内容

    // 图像单独传输 因为需要接口形式

    // 无参构造函数
    public ComposeInfo() {
        this.title = "";
        this.info = "";
    }

}
