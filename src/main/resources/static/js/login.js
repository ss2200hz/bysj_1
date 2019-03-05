function checkUser(logInfo){
    if(!logInfo){
        Materialize.toast('密码错误!', 4000)
    }
}

function signup(){
//    alert("123");
    $.ajax({
    url:"/singup",
    type:"post",
    async:false});
}