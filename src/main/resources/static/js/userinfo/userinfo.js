var pageSize = 10;//每页条数
var nowPageNo;//当前页数
var pageNum;//总页数

function init(){
    var roleid = $('#roleid').val();
    if(roleid != '0'){
        alert("您没有权限查看此页面");
        $('#page').hide();
        return;
    }
    $.ajax({
        url:'/getUserListNum',
        data:{pageSize:pageSize},
        type:'get',
        success:function(jso){
            initPagingDiv(jso.pageNum);
            getPageList(1);
            pageNum = jso.pageNum;
            nowPageNo = 1;
        },
        error:function(){
            alert("通讯错误")
        }
    })
}

//获取用户信息列表
function getPageList(pageNo){
    var requestData = {pageNo:pageNo,pageSize:pageSize}
    nowPageNo = pageNo;
    $.ajax({
    url: "/getUserList",
    type:"get",
    data:requestData,
    success:function(jso){
//        initPagingDiv(jso.pageNum);
        showData(jso);
    },
    error: function(msg){
        alert("通讯错误");
    }
    });
}

function showData(jso){
    var tbHtmlCode = "";
    var infoList = jso.resultList;
    $("#userList").empty();
    for(i=0;i<infoList.length;i++){
        var data = infoList[i];
        var role;
        if(data.roleid == '0'){
            role = '超级管理员';
        }else if(data.roleid == '1'){
            role = '教师';
        }else if(data.roleid == '2'){
            role = '实验室管理员';
        }
        var deleteHtmlBtn = '<button class="btn waves-effect waves-light" onclick="doDelete('+"['"+data.id+"']"+')">删除';
        var editHtmlBtn = '<button class="btn waves-effect waves-light" onclick="doEdit('+"'"+data.id+"'"+')">编辑';
        tbHtmlCode = '<tr>'+
                     '  <td>'+'<input type="checkbox" value="'+data.id+'" >'+'</td>'+
                     '  <td>'+data.id+'</td>'+
                     '  <td>'+data.name+'</td>'+
                     '  <td>'+role+'</td>'+
                     '  <td>'+data.signupDate+'</td>'+
                     '  <td>'+data.inductDate+'</td>'+
                     '  <td>'+deleteHtmlBtn+editHtmlBtn+'</td>'+
                     '</tr>';
        $('#userList').append(tbHtmlCode);
    }
     $('input[type="checkbox"]:not(:checked),[type="checkbox"]:checked').css('opacity',1);
     $('input[type="checkbox"]:not(:checked),[type="checkbox"]:checked').css('pointer-events','all');
}

//获取被选中的id
function getCheckIds(){
    var checkIds = new Array();
    $('input[type="checkbox"]:checked').each(function(){
        checkIds.push($(this).val());
    });
    if(checkIds.length > 0){
        return checkIds;
    }else{
        throw 'empty';
    }
}

function doDelete(ids){
    var idList;
    if(isEmpty(ids)){
        try{
            idList = getCheckIds();
        }catch(error){
            if(error == 'empty'){
                alert("请选择要删除的数据");
                return;
            }
        }
    }else{
        idList = ids;
    }
    var requestData = {dataList:idList};
    $.ajax({
        url:'/editUser',
        type:'delete',
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.succeed){
                alert("删除成功");
                location.reload();
            }else{
                errorList = jso.errorList;
                alert(errorList.length+"条数据删除失败，错误"+jso.errorInfo);
            }
        },
        error:function(){
            alert("通讯错误");
        }
    })
}

//打开编辑页面
function doEdit(id){
    var url = '/userInfoEdit';
    var iWidth = window.screen.width/2;//弹出窗口的宽度;
    var iHeight = window.screen.height/2;//弹出窗口的高度;
    var iTop = (window.screen.height-30-iHeight)/2;//获得窗口的垂直位置;
    var iLeft = (window.screen.width-10-iWidth)/2;//获得窗口的水平位置;
    window.open(url+'?userid='+id,window, 'resizable=no,alwaysRaised=yes,menubar=no,location=no,height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft);
}

