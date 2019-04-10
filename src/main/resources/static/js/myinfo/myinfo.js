//获取用户信息
function getUserInfo(){
    $.ajax({
            url:'/getUserInfo',
            type:'get',
            dataType:'json',
            success:function(jso){
                if(!jso.error){
                    showTbDiv(jso);
                }else{
                    alert("通讯错误！"+jso.errorInfo);
                }
            },
            async: false});
}

//拼装信息列表
function showTbDiv(jso){
    var roleid = jso.roleid;
    var roleName;
    if(roleid=='0'){
        roleName='超级管理员';
        $('#minemessages').hide();
    }else if(roleid=='1'){
        roleName='教师';
    }else if(roleid=='2'){
        roleName='实验室管理员';
    }else{
        roleName = '新用户';
    }
    var html = '<tr>'+
               '   <td>姓名</td>'+
               '   <td>'+jso.username+'</td>'+
               '   <td>工号</td>'+
               '   <td>'+jso.idCard+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>职务</td>'+
               '    <td>'+roleName+'</td>'+
               '    <td>所教课程</td>'+
               '    <td>'+jso.className+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>电话号码</td>'+
               '    <td>'+'<input id="phone" type="text" value="'+jso.phone+'">'+'</td>'+
               '    <td>电子邮箱</td>'+
               '    <td>'+'<input id="email" type="email" value="'+jso.email+'">'+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>入职日期</td>'+
               '    <td>'+jso.inductDate+'</td>'+
               '</tr>';
     $("#userInfo").append(html);
     $('#phone_old').val(jso.phone);
     $('#email_old').val(jso.email);
}

$(document).ready(function(){
    $('.collapsible').collapsible();
  });

function reSet(){
    $('#phone').val($('#phone_old').val());
    $('#email').val($('#email_old').val());
}

function save(){
    var phone = $('#phone').val();
    var email = $('#email').val();
    if(isEmpty(phone) || isEmpty(email)){
        alert("电话和邮箱不能为空！");
        return;
    }
    var requestData = {phone:phone,email:email};
    $.ajax({
        url:'/editUserInfo',
        type:'put',
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.succeed){
                alert("修改成功");
                location.reload();
            }else{
                alert("修改失败 "+jso.errorInfo);
            }
        },
        error:function(){
            alert("通讯错误");
        }
    });
}
