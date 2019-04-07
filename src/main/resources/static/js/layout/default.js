$(function(){
        $('.datepicker').datepicker({
        selectMonths: true,
        selectYears: 15,
        format: 'yyyy/mm/dd',
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

function isEmpty(str){
    if(str == null || str+'' == '' || str == 'undefined'){
        return true;
    }else{
        return false;
    }
}