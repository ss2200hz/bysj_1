document.write('<script th:src=@{/webjars/materializecss/js/materialize.min.js} type="text/javascript"></script>')

function checkUser(){
    var logInfo = $('#logInfo').val();
    alert(logInfo);
    if(!logInfo){
        Materialize.toast('密码错误!', 4000);
    }
}

function signup(){
    $.ajax({
    url:"/singup",
    type:"post",
    async:false});
}

function checkPassword(){
    var pwd = document.getelementbyid("password");
    var pwdAgain = document.getelementbyid("password_again");
    if(pwd == pwdAgain){
        alert("123123");
    }
}
function test(){
    var pwd = document.getelementbyid("password");
    var pwdAgain = document.getelementbyid("password_again");
    alert(pwd);
}

function demo(){
    alert("123");
    var str = $('#name').val();
    $.ajax({
           url:'/index',
           type:'post',
           success:function(jso){
            alert(str);
           }})
}