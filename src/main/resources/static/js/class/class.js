var pageSize = 10;//每页条数
var nowPageNo;//当前页数
var pageNum;//总页数

//加载页面
function init(){
    $.ajax({
        url:'',
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
    url:"",
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
    $("#classList").empty();
}