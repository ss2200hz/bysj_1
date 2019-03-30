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
    $("#myInfo").hide();
    $("#userInfo").hide();
    $("#labInfo").hide();
    $("#classInfo").hide();
    $("#"+str).load("/"+str);
    $("#"+str).show();
}

