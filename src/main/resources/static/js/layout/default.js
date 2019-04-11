$(function(){
        $('.datepicker').datepicker({
        selectMonths: true,
        selectYears: 15,
        format: 'yyyy-mm-dd',
        i18n: {
            cancel: '取消',
            clear: '清除',
            done: '确定',
            previousMonth: '‹',
            nextMonth: '›',
            months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            weekdaysShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            weekdaysAbbrev: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
        }
        });
})

$(document).ready(function(){
$('#checkAll').change(function (){
    $('input[type="checkbox"]').each(function(){
        $(this).prop("checked",$('#checkAll').prop('checked'));
    })
})
})


//$(function (){
    //上一页
    function lastPage(){
        if(nowPageNo > 1){
            getPageList(nowPageNo - 1);
            $("#pagingDiv").find("li").each(function(){
                $(this).removeClass("active");
            });
            $('#li_'+nowPageNo).addClass("active");
            if(nowPageNo <= 1){
                $('#lastPageBtn').addClass('disabled');
            }
            $('#nextPageBtn').removeClass('disabled');
        }
    }

    //下一页
    function nextPage(){
        if(nowPageNo < pageNum){
            getPageList(nowPageNo + 1);
            $("#pagingDiv").find("li").each(function(){
                $(this).removeClass("active");
            });
            $('#li_'+nowPageNo).addClass("active");
            if(nowPageNo == pageNum){
                $('#nextPageBtn').addClass('disabled');
            }
            $('#lastPageBtn').removeClass('disabled');
        }
    }

    //指定页数
    function turnToPage(pageNo){
        getPageList(pageNo);
        nowPageNo = pageNo;
        if(nowPageNo <= 1){
            $('#lastPageBtn').addClass('disabled');
        }else{
            $('#lastPageBtn').removeClass('disabled');
        }
        if(nowPageNo == pageNum){
            $('#nextPageBtn').addClass('disabled');
        }else{
            $('#nextPageBtn').removeClass('disabled');
        }
    }

//加载分页组件
function initPagingDiv(pageNum){
    //下一页，可点击
    var nextPageStr = '<li class="waves-effect" id="nextPageBtn"><a onclick="nextPage()"><i class="material-icons">chevron_right</i></a></li>';
    //下一页，不可点击
    var nextPageDisStr = '<li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>';
    if(pageNum > 1){
        for(var i=2;i<=pageNum;i++){
            var funStr = 'turnToPage('+i+')';
            var liHtml = '<li class="waves-effect" id="li_'+i+'"><a onclick="'+funStr+'">'+i+'</a></li>';
            $("#pagingDiv").append(liHtml);
        }
        $("#pagingDiv").append(nextPageStr);
    }else{
        $("#pagingDiv").append(nextPageDisStr);
    }
    //点击后改变显示效果
    $("#pagingDiv").find("li").each(function(){
        if($(this).attr("id") != 'lastPageBtn' && $(this).attr("id")  != 'nextPageBtn'){
            $(this).click(function(){
                $("#pagingDiv").find("li").each(function(){
                    $(this).removeClass("active");
                });
                $(this).addClass("active");
            })
        }
    })
}
//})

function isEmpty(str){
    if(str == null || str+'' == '' || str == 'undefined'){
        return true;
    }else{
        return false;
    }
}

function isElementInList(list,element){
    if(isEmpty(list) || list.length == 0){
        return false;
    }
    for(var i=0;i<list.length;i++){
        var data = list[i];
        if(element == data){
            return true;
        }
    }
    return false;
}