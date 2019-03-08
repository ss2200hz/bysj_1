function checkUser(logInfo){
    if(!logInfo){
        Materialize.toast('密码错误!', 4000)
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