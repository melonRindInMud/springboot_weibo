package com.webfinalwork.webfinalwork.beans.repository.filePersistence;


import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// 用于将文章的内容及图片持久化
@Repository
public class ArticlePersistence {

    // 文章内容文件默认名称
    public static final String contentFileName = "content.txt";

    // 默认保存的路径(../static)
    public static final String savePath = "D:/编程/web/webfinalwork/src/main/resources/static";

    // 用于将文章内容存储到目标路径下，以txt 格式
    public void saveArticleContent(String content, String filePath) {
        File file = new File(filePath + "/" + ArticlePersistence.contentFileName);       // 选定文件路径
        try {                                        // 创建文件 并处理相关异常
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(file);           // 创建写文件流 并处理相关异常
            writer.write(content);                   //  写入文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {                                    // 关闭文件操作 并处理相关异常
                writer.flush();                      // 清空缓存
                writer.close();                      // 关闭文件
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 将文章相关的图片存储到目标路径下，文件名和格式保持原样
    public void saveImg(MultipartFile img, String filePath) {
        File dest = new File(filePath + '/' + img.getOriginalFilename());    // 然后使用java io 中的类 创建目标文件
        try {
            img.transferTo(dest);  // 最后将前端数据中的文件转换到 目标文件中（同时抛出异常）

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
