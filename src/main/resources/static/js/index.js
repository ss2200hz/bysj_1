function demo(){
    alert("123");
    var str = $('#name').val();
    $.ajax({
           url:'/index',
           type:'post',
           success:function(jso){
            alert(str);
           }})
}