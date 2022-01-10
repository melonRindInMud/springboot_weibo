// thymeleaf 动态生成的页面切换按钮的响应函数，由于按钮是动态生成的，所以没法绑定
function clicked(self) {
    // 获取当前页面URL
    let index = self.innerHTML
    let url = window.location.pathname
    window.location.href = url.substr(0, url.lastIndexOf('/') + 1) + index
}

// 三种不同排序方式的按钮的响应函数
$('#buttonDate').click(function () {
    let index = $('#currentPage').text()
    window.location.href = '/main/date/' + index
})

$('#buttonComment').click(function () {
    let index = $('#currentPage').text()
    window.location.href = '/main/comment/' + index
})

$('#buttonSupport').click(function () {
    let index= $('#currentPage').text()
    window.location.href = '/main/support/' + index
})

// 返回导航页
$('#back').click(function () {
    window.location.href = "/navigation"
})

// 时钟脚本
elemClock = $('#clock')
elemClock.ready(function () {
    clock(elemClock)
})

