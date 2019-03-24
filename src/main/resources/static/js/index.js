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
    $("#"+str).load("/myInfo");
    $("#"+str).show();
}

