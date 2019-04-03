//获取用户信息
function getUserInfo(){
//    alert("123123");
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
               '    <td>'+jso.phone+'</td>'+
               '    <td>电子邮箱</td>'+
               '    <td>'+jso.email+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>入职日期</td>'+
               '    <td>'+jso.inductDate+'</td>'+
               '    <td>入职日期</td>'+
               '    <td><input type="button" onclick="editMyInfo()" value = "修改信息"></td>'+
               '</tr>';
     $("#userInfo").append(html);
//    alert(jso);
}

//退出登录
function editMyInfo(){

}