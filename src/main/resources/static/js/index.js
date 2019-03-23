function init(){
    var roleid = $("#roleid").val();
}

function exitLogin(){
    $.ajax({
        url:'',
        type:'get',
        success:function(jso){}});
}

function showDiv(str){
    $("#mineInfo").hide();
    $("#userInfo").hide();
    $("#labInfo").hide();
    $("#classInfo").hide();
    $("#"+str).show();
    $("#"+str).load("appointment/userinfo");
}

function getUserInfo(){
    $.ajax({
            url:'/userInfo',
            type:'get',
            success:function(jso){
                showTbDiv(jso);
            }});
}

function showTbDiv(jso){
    alert(jso);
}