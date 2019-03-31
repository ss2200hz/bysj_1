var nowPageNo = 1;

function getLaboratoryList(pageNo){
    var requestData = {pageNo:pageNo,pageSize:10}
    $.ajax({
    url:"/laboratory/getLabInfo",
    type:"get",
    data:requestData,
    success:function(jso){
        showData(jso);
    },
    error: function(msg){
        alert("通讯错误");
    }
    })
}

function showData(jso){
    var tbHtmlCode = "";
    var infoList = jso.resultList;
    for (i=0;i<infoList.length;i++){
        var data = infoList[i];
        tbHtmlCode = "<tr>"+
                     "  <td>"+data.laboratoryNo+"</td>"+
                     "  <td>"+data.laboratoryName+"</td>"+
                     "  <td>"+data.laboratoryType+"</td>"+
                     "  <td>"+data.personNum+"</td>"+
                     "  <td>"+data.lastMaintenanceDate+"</td>"+
                     "  <td>"+data.useTimes+"</td>"+
                     "</tr>";
        $("#labList").append(tbHtmlCode);
    }
}

function initPagingDiv(pageNum){
    var nextPageStr = '<li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>'
    if(pageNum > 1){
        for(var i=0;i<pageNum;i++){
            var funStr = 'getLaboratoryList('+i+')';
            var liHtml = '<li class="waves-effect"><a href="'+funStr+'">'+i+'</a></li>';
            $("#pagingDiv").append(liHtml);
        }
        $("#pagingDiv")
    }
}