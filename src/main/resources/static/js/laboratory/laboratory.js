function getLaboratoryList(){
    $.ajax(
    url:"",
    type:"get",
    success:function(jso){
        showData(jso);
    }),
    error: function(msg){
        alert("");
    }
}

function showData(jso){
    var str = "";
    for (i=0;i<jso.length;i++){
        str = "<tr><td>"+jso[i]....;
        $("#laboratory").append(str);
    }
}