var pageSize = 10;//每页条数
var nowPageNo;//当前页数
var pageNum;//总页数

//加载页面
function init(){
    $.ajax({
        url:'class/getclassNum',
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

//获取课程信息列表
function getPageList(pageNo){
    var requestData = {pageNo:pageNo,pageSize:pageSize}
    nowPageNo = pageNo;
    $.ajax({
    url:"/class/classList",
    type:"get",
    data:requestData,
    success:function(jso){
        showData(jso);
    },
    error: function(msg){
        alert("通讯错误");
    }
    });
}

//拼装显示列表
function showData(jso){
    var tbHtmlCode = '';
    var infoList = jso.resultList;
    var roleid = $('#roleid').val();
    $("#classList").empty();
    for(i=0;i<infoList.length;i++){
        var data = infoList[i];
        tbHtmlCode = '<tr>'+
                      '    <td>'+data.classNo+'</td>'+
                      '    <td>'+data.className+'</td>'+
                      '    <td>'+data.classType+'</td>'+
                      '    <td>'+data.teacherNames+'</td>';
        if(roleid == '1'){
            var deleteBtnHtml = '<button class="btn waves-effect waves-light" onclick="doDelete('+"'"+data.classNo+"'"+')">删除';
            var ids = data.teacherIds;
            if(isElementInList(ids,$('#userid').val())){
                var removeSelfBtnHtml = '<button class="btn waves-effect waves-light" onclick="removeSelf('+"'"+data.classNo+"'"+')">退出课程';
                tbHtmlCode += '<td>'+deleteBtnHtml+removeSelfBtnHtml+'</td>';
            }else{
                var addSelfBtnHtml = '<button class="btn waves-effect waves-light" onclick="addSelf('+"'"+data.classNo+"'"+')">加入课程';
                tbHtmlCode += '<td>'+deleteBtnHtml+addSelfBtnHtml+'</td>';
            }
        }
        tbHtmlCode += '</tr>';
        $("#classList").append(tbHtmlCode);
    }
}


//清空搜索框
function clear(){
    $('#number').val("");
    $('#name').val("");
    $('#type').val("");
}

//条件查询
function search(){
    var classNo = Base64.encode($('#number').val());
    var className = Base64.encode($('#name').val());
    var classType = Base64.encode($('#type').val());
    alert(classType);
    if(isEmpty(classNo) && isEmpty(className)&&isEmpty(classType)){
        getPageList(1);
        return;
    }
    var requestData = {classNo:classNo,classType:classType,className:className};
    $.ajax({
        url:"/class/classListByCondition",
        type:"get",
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            showData(jso);
        },
        error: function(msg){
            alert("通讯错误");
        }
        });
}

//删除课程
function doDelete(classNo){
    var requestData = {classNo:classNo};
    $.ajax({
        url:'/class/editClass',
        type:'delete',
        data:requestData,
        success:function(jso){
            if(jso.succeed){
                alert("删除成功");
                location.reload();
            }else{
                alert("删除失败 "+jso.errorInfo);
            }
        },
        error:function(){
            alert("通讯错误");
        }
    });
}

//退出课程
function removeSelf(classNo){
    var requestData = {userid:$('#userid').val(),classNo:classNo};
    $.ajax({
            url:'class/editClassTeacher',
            type:'delete',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("退出成功");
                    location.reload();
                }else{
                    lert("退出失败 "+jso.errorInfo);
                }
            },
            error:function(){
                alert("通讯错误");
            }
        });
}

//加入课程
function addSelf(classNo){
    var requestData = {userid:$('#userid').val(),classNo:classNo};
    $.ajax({
            url:'class/editClassTeacher',
            type:'post',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("加入成功");
                    location.reload();
                }else{
                    lert("加入失败 "+jso.errorInfo);
                }
            },
            error:function(){
                alert("通讯错误");
            }
        });
}