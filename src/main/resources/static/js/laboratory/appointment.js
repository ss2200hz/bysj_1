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
        },
        minDate: new Date()
        });
})
function init(){

}
//选择预约实验室
function appointLab(){
    var labNo = $('#laboratoryNo').val();
    var appointDate = $('#appointDate').val();
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    if(isEmpty(appointDate)||isEmpty(startTime)||isEmpty(endTime)){
        alert("请选择预约的日期和时间");
        return;
    }
    var requestData = {laboratoryNo:labNo,appointDate:appointDate,startTime:startTime,endTime:endTime};
    $.ajax({
        url:'/laboratory/doAppointed',
        type:'post',
        contentType: "application/json;charset=UTF-8",
        dataType:'json',
        data:JSON.stringify(requestData),
        success:function(jso){
            if(jso.result){
                if(confirm("预约成功！")){
                    window.close();
                }else{
                    window.close();
                }
            }else{
                if(!isEmpty(jso.errorInfo)){
                    alert(jso.errorInfo+',请选择其他时间');
                }else{
                    alert("通讯错误");
                }
            }
        },
        error:function(){
            alert("通讯错误");
        }
    })
}
