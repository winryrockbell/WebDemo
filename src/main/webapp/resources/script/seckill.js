//秒杀相关js
var seckill={
    //具体商品的url
    URL:{
        now : function(){
            return '/seckill/time/now';
        },
        exposer : function(seckillId){
            return '/seckill/' + seckillId + '/export';
        },
        execution : function(seckillId, md5){
            return '/seckill/' + seckillId + '/' + md5 +'/execution';
        }
    },
    startKill : function(seckillId, htmlbox) {
        //规定隐藏的html元素
        htmlbox.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function(result){
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer['open']){
                    //侧面证明了java的类集合里面每个元素都被封装成json
                    //根据返回值构造具体的执行url，通过md5来保证
                    var md5 = exposer['md5'];
                    var exeUrl = seckill.URL.execution(seckillId, md5);
                    console.log(exeUrl);
                    //绑定一次点击事件,不用直接绑定click，防止多次点击发送多次请求
                    $('#killBtn').one('click', function(){
                        //或者用$(this)来获取当前jquery选择器的选择的对象
                        $('#killBtn').addClass('disabled');
                        $.post(exeUrl,{}, function(result){
                            if(result && result['success']){
                                var exeResult = result['data'];
                                var state = exeResult['state'];
                                var stateInfo = exeResult['stateInfo'];
                                htmlbox.html('<span class="label label-success">' + stateInfo + '</span>')
                            }
                        })
                    });
                    //前置操作完成，秒杀可以开始，显示按钮
                    htmlbox.show();
                }else{
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.mycountdown(seckillId, now, start, end);
                }
            }else{
                console.log('result:' + result);
            }
        })
    },
    //验证登陆逻辑
    validateLogin : function(phone){
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    mycountdown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('秒杀结束！');
        }else if(nowTime < startTime){
            var killTime = new Date(startTime * 1000 + 1000);
            //这个countdown是插件的方法
            seckillBox.countdown(killTime , function(event){
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function(){
                //时间已到，开始执行事件
                seckill.startKill();
            });
        }else{
            seckill.startKill(seckillId, seckillBox);
        }
    },
    detail:{
        init : function(params){
            //登陆验证，倒计时
            //拿cookie的登陆数据
            var killPhone = $.cookie('loginPhone');
            var startTime = params['startTime']; //js访问json对象的value方式
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //验证登陆信息
            if(!seckill.validateLogin(killPhone)){
                var loginModal = $('#loginModal');
                loginModal.modal({
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#killPhoneBtn').click(function(){
                    var inputPhone = $('#killPhoneKey').val();
                    if(seckill.validateLogin(inputPhone)){
                        //写入cookie,通过jquery插件去写的，后面的参数
                        $.cookie('loginPhone',inputPhone,{expires:7, path:'/seckill'});
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">信息错误！</label>').show(300);
                    }
                });
            }
            //ajax请求系统时间
            $.get(seckill.URL.now(),{},function(result){
                if(result && result['success']){
                    var nowTime = result['data'];
                    seckill.mycountdown(seckillId,nowTime,startTime,endTime);
                }else{
                    console.log('result:' + result);
                }
            });
        }
    }
}