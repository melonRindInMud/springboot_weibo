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
        target.text('?请填写标题')
        target[0].setAttribute('class', 'warning')
    }
    else {
        flag = isValidPattern(self[0])   // 元素内容不为空 判断格式是否正确，同时赋值给相应的标识变量
        if (true === flag) {
            target.text('√标题格式正确')                                       // 更新文本内容
            target[0].setAttribute('class', 'correct') // 通过更新元素class 来 更新样式
        }
        else {
            target.text('×标题格式错误')
            target[0].setAttribute('class', 'error')   // 同上
        }
    }
    return flag
}

// 用于检测文章内容是否足够 并给出提示
// self 是 $Selector 对象 指的是发生变化的元素
// target 是 $Selector 对象 展示提示信息的元素
let CONTENT_MIN = 100        // 文章最小字数

function checkContent(self, target) {
    let len = self.val().trim().length          // 获取input 中的value的长度
    let flag = false                            // 用于返回值
    if (0 === len) {                            // 空的
        target.text('?请填写内容')
        target[0].setAttribute('class', 'warning')
        flag = false
    }
    else if (len >= CONTENT_MIN) {                      // 已经够字数了
        target.text('√已达到字数要求')
        target[0].setAttribute('class', 'correct')
        flag = true
    }
    else {                                      // 字数不够
        let remind = CONTENT_MIN - len
        target.text('×还需输入' + String(remind) + '字')
        target[0].setAttribute('class', 'error')
        flag = false
    }
    return flag
}

let flag1 = false                            // 用于判断标题是否符合格式
let flag2 = false                            // 用于判断内容是否符合要求
let elemSubmitButton = $('#submit')

function checkButtonValid() {                // 用于根据格式是否符合要求决定按钮是禁用还是启用
    if (true === flag1 && true === flag2) {  // 都符合格式要求
        elemSubmitButton.removeAttr('disabled')
    }
    else {
        elemSubmitButton.attr({'disabled': 'disabled'})
    }
}


// 不同元素的 selector 类型元素
let elemTitleInput = $('#titleInput')        // 标题输入框(selector 类型元素 下同)
let elemTitleRemind = $('#titleRemind')      // 标题提示信息元素
let elemContentInput = $('#articleInfo')     // 内容输入框
let elemContentRemind = $('#contentRemind')  // 内容提示信息元素

// 绑定获得焦点时的事件响应函数
elemTitleInput.focus(function () {
    flag1 = onInput(elemTitleInput, elemTitleRemind)
    checkButtonValid()
})

elemContentInput.focus(function () {
    flag2 = checkContent(elemContentInput, elemContentRemind)
    checkButtonValid()
})

// 绑定内容变化时的事件响应函数
elemTitleInput.on('input', function () {
    flag1 = onInput(elemTitleInput, elemTitleRemind)
    checkButtonValid()
})

elemContentInput.on('input', function () {
    flag2 = checkContent(elemContentInput, elemContentRemind)
    checkButtonValid()
})
