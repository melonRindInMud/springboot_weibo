// 提交评论的函数
$('#submitComment').click(function () {
    // 提交用户名 文章id 评论内容
    let articleId = $('#ArticleId').text()
    let userName = $('#UserName').text()
    let comment = $('#Comment').val()
    $.ajax({
        type: 'POST',                                                               // 提交方式POST ajax 中 有6种提交方式
        url: '/checkComment',                                                       // 提交目标地址
        data: {'ArticleId': articleId, 'UserName': userName, 'Comment': comment},   // 提交的数据
        success: function(data) {                                                   // 成功提交以后的回调函数
            // 提交成功之后，将评论数信息更新
            let elemCommentNumber = $('#commentNumber')
            let number = elemCommentNumber.text()
            number = (parseInt(number) + 1).toString()
            elemCommentNumber.text(number)

            let userName = $('#UserName').text()
            let comment = $('#Comment').val()
            let date = timeToDate()

            // 将评论信息更新到页面上
            $('#commentContent').prepend('<div><p>评论时间 <span id="temp0">' + date + '</span></p>\n' +
                '                <p>评论用户 <span id="temp1">' + userName + '</span></p>\n' +
                '                <p>评论内容 <span id="temp2">' + comment + '</span></p></div>')
        }
    })
})

// 将date 时间 转换为年.月.日 时:分 格式
function timeToDate() {
    let date = new Date()                                 // 获取当前时刻

    let dateString = date.getFullYear() + '.'             // 年月日部分信息处理
    if (date.getMonth() < 9)                              // 如果月日中只有一位则使用0进行补位
        dateString += '0' +  (date.getMonth() + 1) + '.'
    else
        dateString += (date.getMonth() + 1) + '.'

    if (date.getDate() < 10)
        dateString += '0' + date.getDate() + ' '
    else
        dateString += date.getDate() + ' '

    let TimeString = ''                                   // 时分秒部分信息处理
    if (date.getHours() < 10)                             // 如果时分秒部分只有一位则使用0进行补位
        TimeString += '0' + date.getHours() + ':'
    else
        TimeString += date.getHours() + ':'

    if (date.getMinutes() < 10)
        TimeString += '0' + date.getMinutes()
    else
        TimeString += date.getMinutes()

    return dateString + TimeString
}

// 点赞函数
$('#supportButton').click(function () {
    // 提交 用户名加文章id
    let articleId = $('#ArticleId').text()
    let userName = $('#UserName').text()
    $.ajax({
        type: 'POST',                                                               // 提交方式POST ajax 中 有6种提交方式
        url: '/addSupport',                                                         // 提交目标地址
        data: {'ArticleId': articleId, 'UserName': userName},                       // 提交的数据
        success: function(data) {                                                   // 返回处理
            // 点赞数 + 1 (显示)
            let elemSupportNumber = $('#supportNumber')
            let supportNumber = parseInt(elemSupportNumber.text()) + 1
            elemSupportNumber.text(supportNumber.toString())

            // 禁用点赞按钮并且 修改提示
            let elemSupportButton = $('#supportButton')
            elemSupportButton.attr("disabled", true)
            elemSupportButton.text('已点过赞')
        }
    })
})