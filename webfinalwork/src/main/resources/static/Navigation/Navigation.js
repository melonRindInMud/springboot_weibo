// 注册页面跳转按钮
$('#register').click(function () {
    window.location.href = "/register"
})

// 登录页面跳转按钮
$('#login').click(function () {
    window.location.href = "/login"
})

// 注销 -- 注销 需要访问服务器，将服务器中当前在线用户信息更新，所以至少需要一个request
$('#logOut').click(function () {
    $.ajax({
        type: 'POST',
        url: '/logOut',
        data: {},                           // 这个请求不需要序列化，全部都在session信息中 后端相比前端获取更加方便
        success: function (data) {          // 以该方法 处理返回信息
            doLogOut()
        }
    })
})

// 用户点赞信息
$('#supports').click(function() {
    let userName = $('#userInfo').text()
    let url = '/support/user/' + userName
    window.location.href = url
})

// 写文章
$('#compose').click(function() {
    window.location.href = '/compose'
})

// 查看所有文章
$('#allArticle').click(function () {
    window.location.href = '/main'
})

// 登录
$('#loginButton').click(function () {
    window.location.href = '/login'
})

// 退出
$('#logoutButton').click(function () {
    $.ajax({
        type: 'POST',
        url: '/logOut',
        data: {},                           // 这个请求不需要序列化，全部都在session信息中 后端相比前端获取更加方便
        success: function (data) {          // 以该方法 处理返回信息
            // 由于只是修改session 中的数据 不会直接影响前端，所以需要手动更新前端的数据来更新显示
            doLogOut()
        }
    })
})

// 注册按钮
$('#registerButton').click(function () {
    window.location.href = '/register'
})

// 找回密码
$('#findPasswordButton').click(function () {
    window.location.href = '/findPassword'
})

// 帮助中心暂时还没有 所以就算了

// 时钟
elemClock = $('#clock')
elemClock.ready(function () {
    clock(elemClock)
})

// ajax 退出以后  界面刷新 以及按钮变得不可用的处理函数
function doLogOut() {
    $('#login').show()
    $('#logOut').css('display', 'none')
    $('#userInfo').text('尚未登录')

    // 将后面四个按钮变为不可用
    $('#myArticle').attr("disabled", true)
    $('#supports').attr("disabled", true)
    $('#comments').attr("disabled", true)
    $('#referMe').attr("disabled", true)
    $('#compose').attr("disabled", true)

    // 登录按钮变得可用
    $('#loginButton').attr("disabled", false)
    // 退出按钮变得不可用
    $('#logoutButton').attr("disabled", true)
}

