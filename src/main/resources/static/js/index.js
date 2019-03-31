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
    if(str == 'myInfo'){
        $("#"+str).load("/"+str);
    }else if(str == 'userInfo'){

    }else if(str == 'labInfo'){
        $("#"+str).load("/laboratory/labInfo")
    }else if(str == 'classInfo'){

    }else{
        return;
    }
    $("#"+str).show();
}

