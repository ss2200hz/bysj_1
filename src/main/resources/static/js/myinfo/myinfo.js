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

function showTbDiv(jso){
    var html = '<tr>'+
                '   <td>姓名</td>'+
                '   <td>'+jso.username+'</td>'+
                '   <td>工号</td>'+
                '   <td>'+jso.idCard+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>课程代码</td>'+
               '    <td>'+jso.classNo+'</td>'+
               '    <td>课程名</td>'+
               '    <td>'+jso.className+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>电话号码</td>'+
               '    <td>'+jso.phone+'</tr>'+
               '    <td>电子邮箱</td>'+
               '    <td>'+jso.email+'</td>'+
               '</tr>'+
               '<tr>'+
               '    <td>入职日期</td>'+
               '    <td>'+jso.inductDate+'</td>'+
               '</tr>';
     $("#userInfo").append(html);
//    alert(jso);
}