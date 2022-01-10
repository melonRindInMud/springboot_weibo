// 标识变量
let isPasswordValid = false
let isConfirmPasswordValid = false

// 各个元素
let elemPassword = $('#password')
let elemConfirmPassword = $('#confirmPassword')
let elemDiv1 = $('#d1')
let elemDiv2 = $('#d2')
let elemR1 = $('#r1')
let elemR2 = $('#r2')
let elemSubmit = $('#submit')

elemSubmit.click(function() {
    let user = $('#userName').text()
    let password = $('#password').val()
    $.ajax({
        type: 'POST',                                                          // 提交方式POST ajax 中 有6种提交方式
        url: '/reset',                                                         // 提交目标地址
        data: {'user': user, 'password': password},                            // 提交的数据
        success: function(data) {                                              // 成功提交以后的回调函数
            alert('修改成功')
            window.location.href = '/navigation'
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

function isIdentical() {
    if (true === isPasswordValid && true === isConfirmPasswordValid) {  // 两个都符合格式以后才判断
        let password1 = elemPassword.val()
        let password2 = elemConfirmPassword.val()
        if (password1 === password2) {
            elemR2.text('√两次密码一致')
            elemR2[0].setAttribute('class', 'correct')
            elemSubmit.removeAttr('disabled')
        }
        else {
            elemR2.text('×两次密码不一致')
            elemR2[0].setAttribute('class', 'error')
            elemSubmit.attr({'disabled': 'disabled'})
        }
    }
    else
        elemSubmit.attr({'disabled': 'disabled'})
}

// 绑定oninput
elemPassword.on('input', function () {
    isPasswordValid = onInput(elemPassword, elemR1)
    isIdentical()
})

elemConfirmPassword.on('input', function () {
    isConfirmPasswordValid = onInput(elemConfirmPassword, elemR2)
    isIdentical()
})

// 绑定focus 和 blur
elemPassword.focus(function () {
    isPasswordValid = onInput(elemPassword, elemR1)
    elemDiv1[0].setAttribute('class', 'hasFocus')
    isIdentical()
})

elemConfirmPassword.focus(function () {
    isConfirmPasswordValid = onInput(elemConfirmPassword, elemR2)
    elemDiv2[0].setAttribute('class', 'hasFocus')
    isIdentical()
})

elemPassword.blur(function () {
    elemDiv1[0].setAttribute('class', '')
})

elemConfirmPassword.blur(function () {
    elemDiv2[0].setAttribute('class', '')
})