function validateupdate(startTime, endTime){
    console.log(startTime);
    console.log(endTime);
}

function show(name, id){
    $('#myModal').append('<input type="hidden" value="' + id + '" name="seckillId"/>');
    $("#myModalLabel").text("修改"+ name + "的秒杀时间");
    $('#myModal').modal();
}

function submmitform(){
    //可以用ajax将表单数据转为json字符串传到controller，这是另一种实现，主要搞清楚转换以及接收的规则即可
    //var theform = $('#updateform').serializeJSON();
   // var stringJson = JSON.stringify(theform);
    var startTime = new Date($("input[name='startTime']").val()).getTime() /1000;
    var endTime = new Date($("input[name='endTime']").val()).getTime() /1000;
    $("input[name='startTime']").val(startTime);
    $("input[name='endTime']").val(endTime);
    var theform = $('#updateform');
    theform.submit();
}
