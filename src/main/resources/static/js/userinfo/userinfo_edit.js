$(document).ready(function(){
    if(isEmpty($('#userid').val())){
        $('#idcard').attr("disabled",false);
    }
})

function init(){
    if(!isEmpty($('#userid').val())){
        $.ajax({
            url:'/getUserInfoById',
            type:'get',
            data:{userid:$('#userid').val()},
            success:function(jso){
                $('#idcard').val(jso.idCard);
                $('#password_hide').val(jso.password);
                $('#name').val(jso.username);
                $('#name_hide').val(jso.username);
                $('#roleid').val(jso.roleid);
                $('#roleid_hide').val(jso.roleid);
                $('#phone').val(jso.phone);
                $('#phone_hide').val(jso.phone);
                $('#email').val(jso.email);
                $('#email_hide').val(jso.email);
                $('#inductDate').val(jso.inductDate);
                $('#inductDate_hide').val(jso.inductDate);
            },
            error:function(jso){
                alert("通讯错误");
            }
        });
    }else{
    }
}

function reSet(){
    $('#name').val($('#name_hide').val());
    $('#roleid').val($('#roleid_hide').val());
    $('#phone').val($('#phone_hide').val());
    $('#email').val($('#email_hide').val());
    $('#inductDate').val($('#inductDate_hide').val());
}

function save(){
    var idcard = $('#idcard').val();
    var password = $('#password').val();
    var name = $('#name').val();
    var roleid = $('#roleid').val();
    var phone =  $('#phone').val();
    var email = $('#email').val();
    var inductDate = $('#inductDate').val();
    if(isEmpty($('#userid').val())){
        var requestType = 'post';
    }else{
        var requestType = 'put';
    }
    var requestData = {id:idcard,password:password,name:name,roleid:roleid,phone:phone,email:email,inductDate:inductDate};
    $.ajax({
        url:'/editUser',
        type:requestType,
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.succeed){
                if(isEmpty($('#userid').val())){
                    alert("保存成功，该用户已可注册");
                    window.close();
                }else{
                    alert("修改成功");
                    window.close();
                }
            }else{
                alert("编辑失败 "+jso.errorInfo)
            }
        },
        error:function(){
            alert("通讯错误");
        }
    });
}

function showpassword (){
    alert($('#password_hide').val());
}