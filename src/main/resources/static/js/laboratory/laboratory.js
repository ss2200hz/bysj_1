var pageSize = 10;//每页条数

//加载页面
function init(){
    $.ajax({
        url:'/laboratory/getLabNum',
        data:{pageSize:pageSize},
        type:'get',
        success:function(jso){
            initPagingDiv(jso.pageNum);
            getLaboratoryList(1);
        },
        error:function(){
            alert("通讯错误")
        }
    })
}

//获取实验室信息列表
function getLaboratoryList(pageNo){
    var requestData = {pageNo:pageNo,pageSize:pageSize}
    $.ajax({
    url:"/laboratory/getLabInfo",
    type:"get",
    data:requestData,
    success:function(jso){
//        initPagingDiv(jso.pageNum);
        showData(jso);
    },
    error: function(msg){
        alert("通讯错误");
    }
    })
}

//拼装显示列表
function showData(jso){
    var tbHtmlCode = "";
    var infoList = jso.resultList;
    $("#labList").empty();
    for (i=0;i<infoList.length;i++){
        var data = infoList[i];
        var appiontButtonHtml = '<button class="btn waves-effect waves-light" onclick="openAppoint('+"'"+data.laboratoryNo+"'"+')">预约'
        tbHtmlCode = "<tr>"+
                     "  <td>"+data.laboratoryNo+"</td>"+
                     "  <td>"+data.laboratoryName+"</td>"+
                     "  <td>"+data.laboratoryType+"</td>"+
                     "  <td>"+data.personNum+"</td>"+
                     "  <td>"+data.lastMaintenanceDate+"</td>"+
                     "  <td>"+data.useTimes+"</td>";
//                     "</tr>";
        if($("#roleid").val() == '0'){

        }else if($("#roleid").val() == '1'){
            tbHtmlCode += '<td>'+appiontButtonHtml+'</td>';
        }else if($("#roleid").val() == '2'){

        }
        tbHtmlCode += "</tr>";
        $("#labList").append(tbHtmlCode);
    }
}

//加载分页组件
function initPagingDiv(pageNum){
    //下一页，可点击
    var nextPageStr = '<li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>';
    //下一页，不可点击
    var nextPageDisStr = '<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>';
    if(pageNum > 1){
        for(var i=2;i<=pageNum;i++){
            var funStr = 'getLaboratoryList('+i+')';
            var liHtml = '<li class="waves-effect"><a onclick="'+funStr+'">'+i+'</a></li>';
            $("#pagingDiv").append(liHtml);
        }
        $("#pagingDiv").append(nextPageStr);
    }else{
        $("#pagingDiv").append(nextPageDisStr);
    }
    //点击后改变显示效果
    $("#pagingDiv").find("li").each(function(){
        $(this).click(function(){
           $("#pagingDiv").find("li").each(function(){
                $(this).removeClass("active");
           });
           $(this).addClass("active");
        })
    })
}

function openAppoint(labNo){
    var url = '/laboratory/app';
    var iWidth = window.screen.width/2;//弹出窗口的宽度;
    var iHeight = window.screen.height/2;//弹出窗口的高度;
    var iTop = (window.screen.height-30-iHeight)/2;//获得窗口的垂直位置;
    var iLeft = (window.screen.width-10-iWidth)/2;//获得窗口的水平位置;
    window.open('/laboratory/appointment?laboratoryNo='+labNo,window, 'resizable=no,alwaysRaised=yes,menubar=no,location=no,height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft);
}

