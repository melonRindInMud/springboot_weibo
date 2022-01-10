// 时钟模块JS 脚本 用于显示时间（精确到秒）

// self 是selector 选择器类型元素(单体)
function clock(self) {
    let date = new Date()                                 // 获取当前时刻

    let dateString = date.getFullYear() + '年'            // 年月日部分信息处理
    if (date.getMonth() < 9)                              // 如果月日中只有一位则使用0进行补位
        dateString += '0' +  (date.getMonth() + 1) + '月'
    else
        dateString += (date.getMonth() + 1) + '月'

    if (date.getDate() < 10)
        dateString += '0' + date.getDate() + '日'
    else
        dateString += date.getDate() + '日'

    let TimeString = ''                                   // 时分秒部分信息处理
    if (date.getHours() < 10)                             // 如果时分秒部分只有一位则使用0进行补位
        TimeString += '0' + date.getHours() + ':'
    else
        TimeString += date.getHours() + ':'

    if (date.getMinutes() < 10)
        TimeString += '0' + date.getMinutes() + ':'
    else
        TimeString += date.getMinutes() + ':'

    if (date.getSeconds() < 10)
        TimeString += '0' + date.getSeconds()
    else
        TimeString += date.getSeconds()

    let WeekString = ''                                   // 星期日期处理
    switch (date.getDay()) {
        case 1:
            WeekString = '星期一'
            break
        case 2:
            WeekString = '星期二'
            break
        case 3:
            WeekString = '星期三'
            break
        case 4:
            WeekString = '星期四'
            break
        case 5:
            WeekString = '星期五'
            break
        case 6:
            WeekString = '星期六'
            break
        case 0:
            WeekString = '星期日'
            break
    }
    self.text(dateString + '\n' + WeekString + '  ' + TimeString)
    setTimeout(clock, 1000, self)
}

