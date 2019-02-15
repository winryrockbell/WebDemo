function getLocalTime(nS) {
        var date = new Date(nS * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        var D = date.getDate() < 10 ?  '0'+date.getDate()+ ' ' : date.getDate()+ ' ';
        var h = date.getHours() < 10 ? '0'+date.getHours()+ ':' : date.getHours()+ ':';
        var m = date.getMinutes() < 10 ? '0'+date.getMinutes()+ ':' : date.getMinutes()+ ':';
        var s = date.getSeconds()< 10 ? '0'+date.getSeconds() : date.getSeconds();
        return Y+M+D+h+m+s;
}

function formatTimeStamp(timestamp, count){
    var d = getLocalTime(timestamp);
    document.getElementById('startTime' + count).innerHTML = d;
}

function formatTimeStamp2(timestamp, count){
    var d = getLocalTime(timestamp);
    document.getElementById('endTime' + count).innerHTML = d;
}


