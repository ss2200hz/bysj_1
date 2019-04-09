var pageSize = 10;//每页条数
var nowPageNo;//当前页数
var pageNum;//总页数

//加载页面
function init(){
    $.ajax({
        url:'/laboratory/getLabNum',
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

//获取实验室信息列表
function getPageList(pageNo){
    var requestData = {pageNo:pageNo,pageSize:pageSize}
    nowPageNo = pageNo;
    $.ajax({
    url:"/laboratory/labList",
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

//拼装显示列表
function showData(jso){
    var tbHtmlCode = "";
    var infoList = jso.resultList;
    $("#labList").empty();
    for (i=0;i<infoList.length;i++){
        var data = infoList[i];
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
            //预约按钮
            var appiontButtonHtml = '<button class="btn waves-effect waves-light" onclick="openAppoint('+"'"+data.laboratoryNo+"'"+')">预约';
            tbHtmlCode += '<td>'+appiontButtonHtml+'</td>';
        }else if($("#roleid").val() == '2'){
            //编辑按钮
            var editButtomHtml = '<button class="btn waves-effect waves-light" onclick="doEdit('+"'"+data.laboratoryNo+"'"+')">编辑';
            //删除按钮
            var deleteButtonHtml = '<button class="btn waves-effect waves-light" onclick="doDelete('+"'"+data.laboratoryNo+"'"+')">删除'
            tbHtmlCode += '<td>'+editButtomHtml+deleteButtonHtml+'</td>';
//            tbHtmlCode += '<td>'+deleteButtonHtml+'</td>'
        }
        tbHtmlCode += "</tr>";
        $("#labList").append(tbHtmlCode);
    }
}

//打开预约页面
function openAppoint(labNo){
    var iWidth = window.screen.width/2;//弹出窗口的宽度;
    var iHeight = window.screen.height/2;//弹出窗口的高度;
    var iTop = (window.screen.height-30-iHeight)/2;//获得窗口的垂直位置;
    var iLeft = (window.screen.width-10-iWidth)/2;//获得窗口的水平位置;
    window.open('/laboratory/appointment?laboratoryNo='+labNo,window, 'resizable=no,alwaysRaised=yes,menubar=no,location=no,height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft);
}


//打开编辑窗口
function doEdit(labNo){
    var url = '/laboratory/edit'
    window.open(url+"?laboratoryNo="+labNo,"编辑实验室");
}

//删除
function doDelete(labNo){
    if(confirm("确定删除这个实验室信息吗？")){
        $.ajax({
            url:'/laboratory/edit',
            type:'delete',
            data:{laboratoryNo:labNo},
            success:function(jso){
                if(jso.succeed){
                    alert("删除成功");
                }else{
                    alert("删除失败");
                }
            },
            error:function(jso){
                alert("通讯错误");
            }
        })
    }
}
