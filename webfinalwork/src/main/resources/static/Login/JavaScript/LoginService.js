// 规定的 最小登录时间差
const LOGIN_REQUEST_INTERVAL = 10000

let isNameValid = false     // 用户名是否符合格式
let isPassWordValid = false // 密码是否符合格式


// 获取本次登录尝试和上一次有效尝试之间的时间差，单位毫秒
function getLoginInterval(date) {
    let time = date.getTime()
    let last_time =  parseInt($.cookie('time'))        // 这里取出来的是string 类型的 cookie中都是
    if (isNaN(last_time))                                  // 如果没有上一次记录, 则返回要求的登录时间间隔+1毫秒
        return LOGIN_REQUEST_INTERVAL + 1
    else                                                   // 如果有记录 就求时间差
        return time - last_time
}

// 判断是否可以提交登录请求，并作出相应的动作和处理
function processCheckLogin() {
    let date = new Date()                                  // 当前时刻
    let interval = getLoginInterval(date)
    if (interval <= LOGIN_REQUEST_INTERVAL)                // 没达到时间间隔
        $('#r3')[0].innerHTML = '还差' + String((LOGIN_REQUEST_INTERVAL - interval)/1000) + '秒可以登录'
    else {                                                 // 达到时间间隔
        $.cookie('time', date.getTime().toString())    // 更新cookie记录的最后一次成功尝试的时间
        ajaxPostRequest()
        $('#r3')[0].innerHTML = '登录请求已经发送'
    }
}

// 提交登录请求的函数 并处理返回的值 (因为是ajax 请求，所以返回的是json)
function ajaxPostRequest() {
    $.ajax({
        type: 'POST',
        url: '/loginCheck',
        data: $('#form1').serialize(),      // 提交表单绑定或包含的数据 参数一定要序列化
        success: function (data) {          // 以该方法 处理返回信息
            if ('ok' === data)              //登录成功
                window.location.href = '/navigation';
            else {
                let elemShow = $('#r3')
                elemShow[0].setAttribute('class', 'error');
                elemShow.text(data)
            }
        }
    })
}

// 数据格式校验 self 必须是 DOM 元素单体
function isValidPattern(self) {
    let pattern = self.pattern      // 正则表达式内容
    let info = self.value           // 被检验的内容
    let regex = new RegExp('^' + pattern + '$')  // 生成正则表达式
    let re = regex.exec(info)       // 检查
    return null != re;
}

// 输入框内容发生变化时的事件响应函数
// self 是 $Selector 对象 指的是发生变化的元素
// target 是 $Selector 对象 展示提示信息的元素
// flag 是 变量 用于记录对应的输入框格式是否符合
function onInput(self, target) {
    let flag
    if ('' === self.val()) {             // 元素内容为空 给出提示信息： 请填写此栏
        flag = false
        target.text('?请填写此栏')
        target[0].setAttribute('class', 'warning')
    }
    else {
        flag = isValidPattern(self[0])   // 元素内容不为空 判断格式是否正确，同时赋值给相应的标识变量
        if (true === flag) {
            target.text('√格式正确')                                       // 更新文本内容
            target[0].setAttribute('class', 'correct') // 通过更新元素class 来 更新样式
        }
        else {
            target.text('×格式错误')
            target[0].setAttribute('class', 'error')   // 同上
        }
    }
    return flag
}

// 用于检测对于当前的输入，按钮是否可用
function isButtonValid() {
    let elemShow = $('#r3')
    if (true === isPassWordValid && true === isNameValid) {             // 输入同时符合格式
        elemShow.text('格式正确,可以提交')                                       // 提示信息
        elemShow[0].setAttribute('class', 'correct')  // 改变样式
        $('#submitInput').removeAttr('disabled')         // 启用按钮
    }
    else if (false === isNameValid) {       // 用户名不符合格式
        if ('' === $('#name').val()) {  // 空的
            elemShow.text('?请填写用户名')
            elemShow[0].setAttribute('class', 'warning')
        }
        else {                              // 不是空的 但是也不符合格式
            elemShow.text('×用户名格式错误')
            elemShow[0].setAttribute('class', 'error')
        }
        $('#submitInput').attr({'disabled': 'disabled'})            // 禁用按钮
    }
    else {
        if ('' === $('#password').val()) {  // 空的
            elemShow.text('?请填写密码')
            elemShow[0].setAttribute('class', 'warning')
        }
        else {
            elemShow.text('×密码格式错误')                                        // 提示信息
            elemShow[0].setAttribute('class', 'error')    // 改变样式
        }
        $('#submitInput').attr({'disabled': 'disabled'})            // 禁用按钮
    }
}


// 绑定登录按钮触发函数：
$('#submitInput').click(processCheckLogin)

// 绑定 两个输入框的事件 (获取焦点和内容变化) 同时动态决定按钮是否能够被使用
elemName = $('#name')
elemName.on('input', function () {
    isNameValid = onInput(elemName, $('#r1'))
    isButtonValid()
})

elemName.focus(function () {
    isNameValid = onInput(elemName, $('#r1'))
    isButtonValid()
})

elemPassWord = $('#password')
elemPassWord.on('input', function () {
    isPassWordValid = onInput(elemPassWord, $('#r2'))
    isButtonValid()
})

elemPassWord.on('input', function () {
    isPassWordValid = onInput(elemPassWord, $('#r2'))
    isButtonValid()
})

// 注册按钮跳转
$('#login').click(function () {
    window.location.href = '/register'
})

// 忘记密码跳转
$('#findPassword').click(function () {
    window.location.href = '/findPassword'
})

