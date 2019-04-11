var userid;
//获取用户信息
function getUserInfo(){
    $.ajax({
            url:'/getUserInfo',
            type:'get',
            dataType:'json',
            success:function(jso){
                if(!jso.error){
                    var roleid = jso.roleid;
                    userid = jso.idCard;
                    if(roleid=='1'){
                        initClassList(userid);
                    }else{
                        $('#minemessages').hide();
                    }
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

//加载课程下拉列表
function initClassList(id){
    $.ajax({
        url:'/class/classListByUser',
        type:'get',
        data:{id:id},
        success:function(jso){
            var list = jso.resultList;
            for(var i=0;i<list.length;i++){
                var data = list[i];
                var html = '<option value="'+data.classNo+'">'+data.className+'</option>'
                $('#classList').append(html);
            }
        },
        error:function(){
            alert("通讯错误");
        }
    });
}

//自动预约实验室
function autoAppointLab(){
    var appointDate = $('#appointDate').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var classNo = $('#classList').val();
    var personNum = $('#personNum').val();
    if(isEmpty(appointDate)||isEmpty(startTime)||isEmpty(endTime)){
        alert("请选择预约的日期和时间");
        return;
    }
    if(isEmpty(classNo)){
       alert("请选择课程");
       return;
    }
    if(isEmpty(personNum)){
        alert("请输入人数");
        return;
    }
    var requestData = {userid:userid,appointDate:appointDate,startTime:startTime,endTime:endTime,classNo:classNo,personNum:personNum};
    $.ajax({
        url:'/laboratory/autoAppointed',
        type:'post',
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.succeed){
                alert("预约成功，您预约的实验室是："+jso.labNo+','+jso.labName);
            }else{
                alert("预约失败，"+errorInfo);
            }
        },
        error:function(){
            alert("通讯错误");
        }
    })
}