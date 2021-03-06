var pageSize = 8;//每页条数
var nowPageNo;//当前页数
var pageNum;//总页数

function init(){
    $(document).ready(function(){
        $('.collapsible').collapsible();
    });
    getlabBaseInfo();
    $.ajax({
        url:'/laboratory/getLabAppointNum',
        type:'get',
        data:{"laboratoryNo":$('#laboratoryNo').val(),"pageSize":pageSize},
        success:function(jso){
             initPagingDiv(jso.pageNum);
             getPageList(1);
             pageNum = jso.pageNum;
             nowPageNo = 1;
        },
        error:function(){
            alert("通讯错误");
        }
    })
}
//获取实验室基本信息
function getlabBaseInfo(){
    var laboratoryNo = $('#laboratoryNo').val();
    $.ajax({
        url:'/laboratory/getLabInfoById',
        type:'get',
        data:{'laboratoryNo':laboratoryNo},
        success:function(jso){
            showBaseInfoDiv(jso);
        },
        error:function(jso){
            alert("通讯错误");
        }
    })
}

//拼装基本信息表
function showBaseInfoDiv(data){
//    var html = '<tr>'+
//               '    <td>实验室编号</td>'+
//               '    <td>'+'<input id="laboratoryNo type="text" value='+data.laboratoryNo+'>'+'</td>'+
//               '</tr>'+
//               '<tr>'+
//               '    <td>实验室名称</td>'+
//               '    <td>'+'<input id="laboratoryName type="text" value='+data.laboratoryName+'>'+'</td>'+
//               '</tr>'+
//               '<tr>'+
//               '    <td>实验室类型</td>'+
//               '    <td>'+'<input id="laboratoryType type="text" value='+data.laboratoryType+'>'+'</td>'+
//               '</tr>'+
//               '<tr>'+
//               '    <td>最大可容纳人数</td>'+
//               '    <td>'+'<input id="personNum type="text" value='+data.personNum+'>'+'</td>'+
//               '</tr>'+
//               '<tr>'+
//               '    <td>上次维护日期</td>'+
//               '    <td>'+'<input id="lastMaintenanceDate" class="datepicker" value='+data.lastMaintenanceDate+'>'+'</td>'+
//               '</tr>'+
//               '<tr>'+
//               '    <td>已使用次数</td>'+
//               '    <td>'+'<input id="useTimes type="text" value='+data.useTimes+'>'+'</td>'+
//               '</tr>'
    $('#labNo').attr('value',data.laboratoryNo);
    $('#labNo_hidden').attr('value',data.laboratoryNo);
    $('#laboratoryName').attr('value',data.laboratoryName);
    $('#laboratoryName_hidden').attr('value',data.laboratoryName);
    $('#laboratoryType').attr('value',data.laboratoryType);
    $('#laboratoryType_hidden').attr('value',data.laboratoryType);
    $('#personNum').attr('value',data.personNum);
    $('#personNum_hidden').attr('value',data.personNum);
    $('#lastMaintenanceDate').attr('value',data.lastMaintenanceDate);
    $('#lastMaintenanceDate_hidden').attr('value',data.lastMaintenanceDate);
    $('#useTimes').attr('value',data.useTimes);
    $('#useTimes_hidden').attr('value',data.useTimes);
    $('#isCanUse').val(data.laboratoryState);
    $('#isCanUse_hidden').val(data.laboratoryState);
}

//重置
function reSet(){
    $('#baseInfo').find('tr').each(function(){
        var inputDiv = $(this).children().eq(1).find('input');
        var hiddenDiv = $(this).children().eq(2);
        var selectDiv = $(this).children().eq(1).find('select');
        inputDiv.val(hiddenDiv.val());
        selectDiv.val(hiddenDiv.val());
    });
}

//保存
function save(){
    var laboratoryNo = $('#labNo').val();
    var laboratoryName = $('#laboratoryName').val();
    var laboratoryType = $('#laboratoryType').val();
    var personNum = $('#personNum').val();
    var lastMaintenanceDate = $('#lastMaintenanceDate').val();
    var useTimes = $('#useTimes').val();
    var isCanUse = $('#isCanUse').val();

    if(!isEmpty(laboratoryNo) && !isEmpty(laboratoryName) && !isEmpty(laboratoryType)
            && !isEmpty(personNum) && !isEmpty(lastMaintenanceDate) && !isEmpty(useTimes)){
        var requestData = {oldLabNo:$('#labNo_hidden').val(),laboratoryNo:laboratoryNo,laboratoryName:laboratoryName,
        laboratoryType:laboratoryType,personNum:personNum,lastMaintenanceDate:lastMaintenanceDate,useTimes:useTimes,
        isCanUse:isCanUse};
        $.ajax({
            url:'/laboratory/edit',
            type:'put',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("保存成功");
                    window.close();
                }else{
                    alert(jso.errorInfo);
                }
            },
            error:function(){
                alert("通讯错误");
            }
        });
    }else{
        alert("数据不能为空!");
        return;
    }
}

//实验室预约记录列表
function getPageList(pageNo){
    var requestData = {'laboratoryNo':$('#laboratoryNo').val(),pageNo:pageNo,pageSize:pageSize}
    nowPageNo = pageNo;
    $.ajax({
        url:"/laboratory/labAppointHistory",
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

//显示列表
function showData(jso){
    var tbHtmlCode = "";
    var infoList = jso.resultList;
    $("#appointHistory").empty();
    for(i=0;i<infoList.length;i++){
        var data = infoList[i];
        var appState;
        if(data.appointedState == 0){
            appState = '未完成';
        }else if(data.appointedState == 1){
            appState = '已完成';
        }else if(data.appointedState == 2){
            appState = '已退回';
        }
        tbHtmlCode = '<tr>'+
                     '  <td>'+'<input type="checkbox" value="'+data.id+'" >'+'</td>'+
                     '  <td>'+data.appointedUser+'</td>'+
                     '  <td>'+data.appointedStartTime+'</td>'+
                     '  <td>'+data.appointedEndTime+'</td>'+
                     '  <td>'+appState+'</td>';
        var deleteButtonHtml = '<button class="btn waves-effect waves-light" onclick="doDelete('+"'"+data.id+"'"+')">删除';
        var btnHtml= '<button class="btn waves-effect waves-light" onclick="back('+"'"+data.id+"'"+')">退回';
        if(data.appointedState == 0){
            tbHtmlCode += '<td>'+deleteButtonHtml+btnHtml+'</td>';
        }else{
            tbHtmlCode += '<td>'+deleteButtonHtml+'</td>';
        }
        tbHtmlCode += "</tr>";
        $("#appointHistory").append(tbHtmlCode);
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

//单个删除
function doDelete(appointId){
    var ids = new Array();
    ids.push(appointId);
    var requestData = {dataList:ids};
    $.ajax({
            url:'/laboratory/labAppointHistory',
            type:'delete',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("删除成功");
                    location.reload();
                }else{
                    alert('删除失败'.jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
            });
}

//批量删除
function doDeletes(){
    try{
        var ids = getCheckIds();
        var requestData = {dataList:ids};
        $.ajax({
            url:'/laboratory/labAppointHistory',
            type:'delete',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("删除成功");
                    location.reload();
                }else{
                    alert('删除失败'+jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
        });
    }catch(error){
        if(error == 'empty'){
            alert("请选择要删除的数据");
        }
    }
}

//退回
function back(id){
    var ids = new Array();
    ids.push(id);
    var requestData = {dataList:ids};
    $.ajax({
            url:'/laboratory/labAppointHistory',
            type:'put',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("已退回该预约");
                    location.reload();
                }else{
                    alert('退回失败'+jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
            });
}
//批量退回
function backs(){
    try{
        var ids = getCheckIds();
        var requestData = {dataList:ids};
        $.ajax({
            url:'/laboratory/labAppointHistory',
            type:'put',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("已退回这些预约");
                    location.reload();
                }else{
                    alert('退回失败'+jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
        });
    }catch(error){
        if(error == 'empty'){
            alert("请选择要退回的预约");
        }
    }
}

//设为已完成
function setOver(){
    try{
        var ids = getCheckIds();
        var requestData = {dataList:ids};
        $.ajax({
            url:'/laboratory/setoverlabAppointment',
            type:'put',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("设置成功");
                    location.reload();
                }else{
                    alert('设置失败'+jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
        });
    }catch(error){
        if(error == 'empty'){
            alert("请选择要退回的预约");
        }
    }
}

//设为未完成
function setNotOver(){
    try{
        var ids = getCheckIds();
        var requestData = {dataList:ids};
        $.ajax({
            url:'/laboratory/setnotoverlabAppointment',
            type:'put',
            contentType: "application/json;charset=UTF-8",
            dataType:'json',
            data:JSON.stringify(requestData),
            success:function(jso){
                if(jso.succeed){
                    alert("设置成功");
                    location.reload();
                }else{
                    alert('设置失败'+jso.errorInfo);
                }
            },error: function(msg){
                    alert("通讯错误");
                }
        });
    }catch(error){
        if(error == 'empty'){
            alert("请选择要退回的预约");
        }
    }
}

$(document).ready(function(){
$('#checkAll').change(function (){
    $('input[type="checkbox"]').each(function(){
        $(this).prop("checked",$('#checkAll').prop('checked'));
    })
})
})
