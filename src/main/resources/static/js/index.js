function init(){
    var roleid = $("#roleid").val();
}

function exitLogin(){
    $.ajax({
        url:'/exitLogin',
        type:'get',
        success:function(jso){
            alert("退出成功,请重新登陆");
            location.reload();
        }
    });
}

function showDiv(str){
    $("#myInfo").hide();
    $("#userInfo").hide();
    $("#labInfo").hide();
    $("#classInfo").hide();
    if(str == 'myInfo'){
        $("#"+str).load("/"+str);
    }else if(str == 'userInfo'){
        $("#"+str).load("/userInfo")
    }else if(str == 'labInfo'){
        $("#"+str).load("/laboratory/labInfo")
    }else if(str == 'classInfo'){
        $("#"+str).load("/class/class_info")
    }else{
        return;
    }
    $("#"+str).show();
}

