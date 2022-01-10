// 注册部分前端业务脚本 主要采用jquery 编写 部分使用原生html(因为那些部分原生html表述比较清晰)
// js 中 所有参数传递本质是传值，但是由于复杂类型以引用方式实现，所以实际效果是 简单类型传值 复杂类型传引用
let isNameValid = false        //  用于动态确定用户名是否符合格式
let isPassWordValid = false    //  用于动态确定密码是否符合格式
let isEmailValid = false       //  用于动态确定邮箱是否符合格式
let twoPasswordEqual = false   //  用于动态确定两次密码是否相等
let isUserNameNotDuplicated = false   // 用于动态确定数据库中是否有用户名和这个相同
let isTreatySinged = false            // 是否签署了霸王条款

// 注册按钮
$('#submit').click(function () {
    // 四个标识必须都是true 才能提交
    if (true === isUserNameNotDuplicated && true === isPassWordValid && true === isEmailValid
        && true === twoPasswordEqual && true === isNameValid && true === isTreatySinged) {
        $.ajax({
            type: 'POST',                           // 提交方式POST ajax 中 有6种提交方式
            url: '/checkRegister',                  // 提交目标地址
            data: $('#registerForm').serialize(),   // 提交表单绑定的数据
            success: function (data) {              // 成功提交以后的回调函数
                registerSuccess(data)
            }
        })
    }
    else {  // 不是 给出提示信息
        if (false === isUserNameNotDuplicated || false === isNameValid)  // 用户名重复 或不符合要求
            $('#userName').focus().select()
        else if (false === isPassWordValid)   // 密码不符合要求
            $('#passWord').focus().select()
        else if (false === twoPasswordEqual)  // 密码确认
            $('#passWordConfirm').focus().select()
        else if (false === isEmailValid)      // 邮箱
            $('#email').focus().select()
        else if (false === isTreatySinged)    // 没有签署霸王条款
            alert('请签署我们的条款')
    }
})

// 回调函数内容
function  registerSuccess(data) {
    let elemBody = $('#registerForm')
    if (true === data) {                 // 提交成功
        let info = '<p id="feedBack">注册成功 您的用户名是 </br>' + $('#userName').val() + '</p>' +
            '<a id="jump" href="/login">点击登录</a>'
        elemBody.empty()
        elemBody.css('transform', 'translateX(490px)')
        elemBody.html(info)
    }
    else {                              // 提交失败
        elemBody.empty()
        elemBody.text('出现了一点小问题，请您不要修改前端数据并再试一遍')
    }
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


// 密码确认输入框内容改变时的事件响应函数
function confirmPassWordOnInput() {
    let selector = $('#r3')
    if ($('#passWord').val() === $('#passWordConfirm').val()) {         // 相等
        twoPasswordEqual = true;
        selector.text('√两次输入的密码一致')                                // 更新内容
        selector[0].setAttribute('class', 'correct')  // 通过更新class 更新样式
    }
    else {  // 不相等
        twoPasswordEqual = false;
        selector.text('×两次输入的密码不一致')
        selector[0].setAttribute('class', 'error')    // 同上
    }
}


// 检查用户名是否重名 (需要使用ajax 查询数据库) (用户名输入框 Onchange 事件响应函数调用的函数)
function checkUserNameDuplicated() {
    if (isNameValid) {  // 名字符合格式才检验 否则不检验
        let userName = $('#userName').val()
        $.ajax({
            type: 'POST',                           // 提交方式POST ajax 中 有6种提交方式
            url: '/checkUserName',                  // 提交目标地址
            data: {'userName': userName},           // 提交数据
            success: function (data) {              // 成功提交以后的回调函数
                if (1 === data) {                   // 存在
                    isUserNameNotDuplicated = false
                    let target = $('#r1')
                    target.text('!该用户名已存在')
                    target[0].setAttribute('class', 'error')
                }
                else {
                    isUserNameNotDuplicated = true
                    let target = $('#r1')
                    target.text('√该用户名可使用')
                    target[0].setAttribute('class', 'correct')
                }
            }
        })
    }
}

// 事件响应绑定
elemUserName = $("#userName")
elemPassWord = $("#passWord")
elemPasWordConfirm = $('#passWordConfirm')
elemEmail = $('#email')



// 绑定 用户名输入每次失去焦点焦点后值发生变化的事件响应函数：
elemUserName.change(function () {
    checkUserNameDuplicated()
})


// 绑定确认密码输入框的内容发生变化时的动作
$("#passWordConfirm").on('input', function () {
    confirmPassWordOnInput()
})



// 绑定三个输入框的内容发生变化时的动作
elemUserName.on('input', function () {
    isNameValid = onInput($('#userName'), $('#r1'))
})

// 密码改动 确认密码不动也可能会导致两次密码变得相同，所以还需要判断一下
elemPassWord.on('input', function () {
    isPassWordValid = onInput($('#passWord'), $('#r2'))
    confirmPassWordOnInput()
})

elemEmail.on('input', function () {
    isEmailValid = onInput($('#email'), $('#r4'))
})


// 绑定三个输入框获取焦点时的动作
elemUserName.focus(function () {
    isNameValid = onInput($('#userName'), $('#r1'))
    $('#d1')[0].setAttribute('class', 'hasFocus')
})

// 密码改动 确认密码不动也可能会导致两次密码变得相同，所以还需要判断一下
elemPassWord.focus(function () {
    isPassWordValid = onInput($('#passWord'), $('#r2'))
    confirmPassWordOnInput()
    $('#d2')[0].setAttribute('class', 'hasFocus')
})

elemPasWordConfirm.focus(function () {
    $('#d3')[0].setAttribute('class', 'hasFocus')
})

elemEmail.focus(function () {
    isEmailValid = onInput($('#email'), $('#r4'))
    $('#d4')[0].setAttribute('class', 'hasFocus')
})

// 失去焦点
elemUserName.blur(function () {
    $('#d1')[0].setAttribute('class', '')
})

elemPassWord.blur(function () {
    $('#d2')[0].setAttribute('class', '')
})

elemPasWordConfirm.blur(function () {
    $('#d3')[0].setAttribute('class', '')
})

elemEmail.blur(function () {
    $('#d4')[0].setAttribute('class', '')
})

// 霸王条款选项:
$('#agreeTreaty').click(function () {
    isTreatySinged = false === isTreatySinged;
})

elemClock = $('#clock')
// 绑定时钟显示元素
elemClock.ready(function () {
    clock(elemClock)
})


