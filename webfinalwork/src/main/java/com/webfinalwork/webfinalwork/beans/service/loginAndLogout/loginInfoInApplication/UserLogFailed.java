package com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication;

import lombok.Data;

import java.util.Date;

// 用于记录用户登录失败的信息
@Data
public class UserLogFailed {

    public static final long LOCK_DURATION = 3600 * 1000;   // 用户锁定时间

    public static final int MAX_TRIES = 3;                  // 用户被锁定前，最大的失败次数

    private String userName;                          // 登录的用户名

    private long tryMoment;                           // 登录尝试的时刻

    private int tryTimes;                             // 尝试次数

    // 创建用户登录失败信息（以当前时间为第一次登录失败时间）
    public UserLogFailed(String userName) {
        this.userName = userName;
        this.tryMoment = new Date().getTime();        // 用当前时刻作为时刻
        this.tryTimes = 1;
    }

    // 增加一次失败(以当前时间作为最后一次失败信息)
    public void Update() {
        this.tryMoment = new Date().getTime();        // 更新时间
        this.tryTimes += 1;
    }

    // 判断当前时刻用户是否被锁定 如果返回 0  则代表不被锁定，如果返回正整数，则代表被锁定（剩余锁定时间）
    public long userLockedTime() {
        long current = new Date().getTime();
        // 如果尝试次数是最大失败的倍数 同时当前时刻与上一次失败的时间之差小于锁定时间，则代表锁定，否则不被锁定
        if (0 == this.tryTimes % UserLogFailed.MAX_TRIES) {
            return Math.max(this.tryMoment + UserLogFailed.LOCK_DURATION - current, 0);
        }
        else
            return 0;
    }
}
