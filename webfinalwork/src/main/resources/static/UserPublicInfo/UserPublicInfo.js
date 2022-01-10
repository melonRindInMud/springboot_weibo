// 登录 (get 页面直接跳转)
$('#loginButton').click(function () {
    window.location.href = '/login'
})

// 关注用户 (ajax 请求)
$('#focusButton').click(function () {
    let targetUser = $('#targetUser').text()
    $.ajax({
        type: 'POST',                                                    // 提交方式POST ajax 中 有6种提交方式
        url: '/addFocus',                                                // 提交目标地址
        data: {'targetUser': targetUser},                                // 提交的数据
        success: function(data) {                                        // 返回处理
            // 当前页面被关注数 + 1
            let elemBeFocus = $('#numberBeFocus')
            let beFocus = parseInt(elemBeFocus.text()) + 1
            elemBeFocus.text(beFocus)

            // 隐藏关注按钮
            $('#focusButton').css('display', 'none')

            // 开启取消关注按钮
            $('#unFocusButton').show()
        }
    })
})

// 取消关注用户 (ajax 请求)
$('#unFocusButton').click(function () {
    let targetUser = $('#targetUser').text()
    $.ajax({
        type: 'POST',                                                    // 提交方式POST ajax 中 有6种提交方式
        url: '/deleteFocus',                                                // 提交目标地址
        data: {'targetUser': targetUser},                                // 提交的数据
        success: function(data) {                                        // 返回处理
            // 当前页面被关注数 - 1
            let elemBeFocus = $('#numberBeFocus')
            let beFocus = parseInt(elemBeFocus.text()) - 1
            elemBeFocus.text(beFocus)

            // 隐藏取消关注按钮
            $('#unFocusButton').css('display', 'none')

            // 开启关注按钮
            $('#focusButton').show()
        }
    })
})

// 返回主界面的按钮

$('#back').click(function () {
    window.location.href = "/navigation";
})