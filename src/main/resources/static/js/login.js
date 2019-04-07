function checkUser(){
    var loginname = $("#name").val();
    var password = $("#password").val();
    if(isEmpty(loginname) || isEmpty(password)){
        alert("用户名和密码不能为空！");
        return false;
    }
    var data = {"loginname":loginname,"password":hex_md5($("#password").val())};
    $.ajax({
        url:'/checkUser',
        type:'get',
        data:data,
        dataType:'json',
        success:function(jso){
            if(jso.successed){
//               successed $.ajax({
//                    url:"/index",
//                    type:"get",
//                    success:function(){
//                    }
//                })
                window.location.href="/index";
            }else{
                alert(jso.errorInfo);
            }
        }
    });
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