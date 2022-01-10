package com.webfinalwork.webfinalwork.beans.service.loginAndLogout.loginInfoInApplication;

import java.util.Vector;

// 用于记录用户登录失败的次数（密码错误）
public class UserLogFailedTable {

    private Vector<UserLogFailed> info;

    // 默认构造函数
    public UserLogFailedTable() {
        this.info = new Vector<UserLogFailed>();
    }

    // 增加一次指定用户的失败次数
    public void Update(String userName) {
        int flag = -1;
        for (int i = 0; -1 == flag && i < this.info.size(); i++) {  // 遍历
            if (info.elementAt(i).getUserName().equals(userName)) { // 是这个用户
                 flag = 1;
                 info.elementAt(i).Update();                        // 为该用户增加一次失败次数
            }
        }
        if (-1 == flag)                                             // 还没有该用户
            info.addElement(new UserLogFailed(userName));
    }

    // 判断指定用户是否被锁定
    // 没有被锁定返回 0  锁定返回剩余锁定时间
    public long isUserLocked(String userName) {
        for (int i = 0; i < this.info.size(); i++) {  // 遍历
            if (info.elementAt(i).getUserName().equals(userName)) { // 是这个用户
                return info.elementAt(i).userLockedTime();
            }
        }
        return 0;  // 能出来说明 里面没有
    }
}
