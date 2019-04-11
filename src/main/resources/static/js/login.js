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
    type:"get",
    async:false});
}

function checkPassword(){
    var pwd = $('#password').val();
    var pwdAgain = $('#password_again').val();
    if(pwd != pwdAgain){
        alert("两次输入的密码不一致!");
        return false;
    }
    return true;
}

//注册
function usersignup(){
    if(!checkPassword()){
        return;
    }
    var userid = $('#name').val();
    var roleid = $('#roleid').val();
    var password = $('#password').val();
    var phone = $('#phone').val();
    var email = $('#email').val();
    if(isEmpty(userid)||isEmpty(roleid)||isEmpty(password)||isEmpty(phone)||isEmpty(email)){
        alert("请填写全信息");
        return;
    }
    var requestData = {id:userid,roleid:roleid,password:password,phone:phone,email:email};
    $.ajax({
        url:'/inseruser',
        type:'post',
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.succeed){
                alert("注册成功！");
                window.history.back(-1);
            }else{
                alert("注册失败,"+jso.errorInfo);
            }
        },
        error:function(jso){
            alert("通讯错误");
        }
    });
}