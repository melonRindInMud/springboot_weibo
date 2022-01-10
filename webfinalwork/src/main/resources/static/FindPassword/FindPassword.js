// 用于判定当前的信息是否可以提交
let userValid = false
let emailValid = false
let userExist = false

// 各个元素
let elemUser = $('#userName')
let elemEmail = $('#email')

let elemR1 = $('#r1')
let elemR2 = $('#r2')

let elemButton = $('#submit')

// 绑定两个输入框获取焦点时和失去焦点时，对应的div的样式变化
elemUser.focus(function () {
    $('#d1')[0].setAttribute('class', 'hasFocus')
})

elemEmail.focus(function () {
    $('#d2')[0].setAttribute('class', 'hasFocus')
})

elemUser.blur(function () {
    $('#d1')[0].setAttribute('class', '')
})

elemEmail.blur(function () {
    $('#d2')[0].setAttribute('class', '')
})

// 提交按钮响应
elemButton.click(function () {
    $.ajax({
        type: 'POST',                           // 提交方式POST ajax 中 有6种提交方式
        url: '/checkFindPassword',              // 提交目标地址
        data: $('#form1').serialize(),           // 提交数据
        success: function (data) {              // 成功提交以后的回调函数
            if ('no' === data)
                alert('注册邮箱不正确')
            else {
                alert('邮件已发送')
                window.location.href = '/navigation'
            }
        }
    })
})

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
            target.text('√格式正确')                                      // 更新文本内容
            target[0].setAttribute('class', 'correct') // 通过更新元素class 来 更新样式
        }
        else {
            target.text('×格式错误')
            target[0].setAttribute('class', 'error')   // 同上
        }
    }
    return flag
}

// 动态判断当前输入情况 提交按钮是否可用
function isSubmitValid() {
    if (true === userValid && true === emailValid && true === userExist)
        elemButton.removeAttr('disabled')
    else
        elemButton.attr({'disabled': 'disabled'})
}

// 查询当前输入的用户是否存在
function isUserExist() {
    if (userValid) {  // 名字符合格式才检验 否则不检验
        let userName = $('#userName').val()
        $.ajax({
            type: 'POST',                           // 提交方式POST ajax 中 有6种提交方式
            url: '/checkUserName',                  // 提交目标地址
            data: {'userName': userName},           // 提交数据
            success: function (data) {              // 成功提交以后的回调函数
                if (1 === data) {                   // 存在
                    userExist = true
                    let target = $('#r1')
                    target.text('√用户名存在')
                    target[0].setAttribute('class', 'correct')
                }
                else {
                    userExist = false
                    let target = $('#r1')
                    target.text('!该用户名不存在')
                    target[0].setAttribute('class', 'error')
                }
            }
        })
    }
}

elemUser.on('input', function () {
    userValid = onInput(elemUser, elemR1)
    isSubmitValid()
})

elemEmail.on('input', function () {
    emailValid = onInput(elemEmail, elemR2)
    isSubmitValid()
})

elemUser.focus('input', function () {
    userValid = onInput(elemUser, elemR1)
    isSubmitValid()
})

elemEmail.focus('input', function () {
    emailValid = onInput(elemEmail, elemR2)
    isSubmitValid()
})

// 每次输入的用户名发生改变且失去焦点以后进行一次判断，判断用户名是否存在
elemUser.change(function () {
    isUserExist()
})
