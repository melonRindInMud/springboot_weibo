package com.webfinalwork.webfinalwork.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 这个类给服务器的指定路径添加一个本地映射，一般用于下载上传的文件（不用访问服务器真实的所在路径）
@Configuration
public class UploadPathConfig implements WebMvcConfigurer {

    public static final String serverUpLoadFilePath = "/upLoadFiles/";

    public static final String localUpLoadFilePath = "D:/WebFinalWorkData";


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  前面是访问路径 后面是映射到本地的路径（前面要加file:）前面最后的需要加* 但是后面的不需要加*
        registry.addResourceHandler(serverUpLoadFilePath + "*")
                .addResourceLocations("file:" + localUpLoadFilePath);
    }
}
