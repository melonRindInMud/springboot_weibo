let input1HasFocus = false
let input2HasFocus = false

$('#form1').hover(function () {
    $('#form1')[0].className = 'cHover'
    let plist = $('p')
    plist[0].className = 'on'
    plist[1].className = 'on'
    plist[2].className = 'on'
    plist[3].className = 'on'
    plist[4].className = 'on'
    $('h2')[0].className = 'on'
    $('p > input')[0].className = 'cHover'
    $('p > input')[1].className = 'cHover'
    $('#submitInput')[0].className = 'on'
})

$('#form1').mouseleave(function () {
    if (false === input1HasFocus && false === input2HasFocus) {
        $('#form1')[0].className = ''
        let plist = $('p')
        plist[0].className = ''
        plist[1].className = ''
        plist[2].className = ''
        plist[3].className = ''
        plist[4].className = ''
        $('h2')[0].className = ''
        $('p > input')[0].className = ''
        $('p > input')[1].className = ''
        $('#submitInput')[0].className = ''
    }
})


$('#name').on('input', function () {
    $('#name')[0].className = 'onInput'
})

$('#password').on('input', function () {
    $('#password')[0].className = 'onInput'
})


$('#name').focus(function () {
    input1HasFocus = true
    $('#inputContainer1')[0].className = 'cFocus'
})

$('#password').focus(function () {
    input2HasFocus = true
    $('#inputContainer2')[0].className = 'cFocus'
})


$('#name').blur(function () {
    input1HasFocus = false
    $('#inputContainer1')[0].className = 'on'
    $('p > input')[0].className = 'cHover'
})

$('#password').blur(function () {
    input2HasFocus = false
    $('#inputContainer2')[0].className = 'on'
    $('p > input')[1].className = 'cHover'
})






