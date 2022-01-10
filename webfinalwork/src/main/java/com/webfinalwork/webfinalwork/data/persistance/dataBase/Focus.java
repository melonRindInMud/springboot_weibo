package com.webfinalwork.webfinalwork.data.persistance.dataBase;

import com.webfinalwork.webfinalwork.util.DateFormTransformer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.exception.DataException;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

// 关注信息JPA类
@Data
@Entity
@Table(name="focus")
@NoArgsConstructor
public class Focus {

    @Id
    private long focusId;        // 关注ID 以 关注的时刻决定，一个用户对另一个用户关注以后再取消 再次关注ID会不同

    @Basic
    private String date;         // 起始关注的日期

    @Basic
    private String ownerName;    // 关注的发起者

    @Basic
    private String targetName;   // 关注的目标

    // 构造函数
    public Focus(String ownerName, String targetName) {
        this.focusId = new Date().getTime();
        this.ownerName = ownerName;
        this.targetName = targetName;
        this.date = DateFormTransformer.TimeToDate(this.focusId);
    }
}
