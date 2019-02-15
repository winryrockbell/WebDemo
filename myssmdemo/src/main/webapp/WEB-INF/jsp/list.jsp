<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>秒杀列表页</title>
    <!--静态包含，类似于宏，最终连在一起以一个servlet输出-->
    <%@include file="common/head.jsp"%>
</head>
<link href="/resources/lib/css/datepicker/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<script type="text/javascript" src="/resources/script/tool/format.js"></script>
<script type="text/javascript" src="/resources/script/seckilllist.js"></script>
<body>
    <!-- 页面显示部分-->
    <div class ="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>秒杀列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>操作栏</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ele" items="${seckilllist}" varStatus="status">
                            <tr>
                                <td>${ele.name}</td>
                                <td>${ele.number}</td>
                                <td id="startTime${status.count}">
                                </td>
                                <script type="text/javascript">
                                    formatTimeStamp(${ele.startTime}, ${status.count});
                                </script>
                                <td id="endTime${status.count}">
                                </td>
                                <script type="text/javascript">
                                    formatTimeStamp2(${ele.endTime},  ${status.count});
                                </script>
                                <td>
                                    <fmt:formatDate value="${ele.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a class="btn btn-info" href="/seckill/${ele.seckillId}/detail" target="_blank">
                                        详情
                                    </a>
                                    <a class="btn btn-info" onclick='show("${ele.name}", "${ele.seckillId}")' target="_self">
                                        修改
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <form action="/seckill/update" method="post" id="updateform">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">修改时间</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>开始时间</label>
                            <div class="row">
                                <div class='col-sm-6'>
                                    <div class="form-group">
                                        <div class='input-group date  datetimepicker'>
                                            <input type='text' class="form-control" name="startTime"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>结束时间</label>
                            <div class="row">
                                <div class='col-sm-6'>
                                    <div class="form-group">
                                        <div class='input-group date datetimepicker'>
                                            <input type='text' class="form-control" name="endTime"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                        <button type="button" onclick="submmitform()" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</body>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.serializeJSON/2.9.0/jquery.serializejson.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<script src="/resources/lib/script/moment/moment-with-locales.min.js"></script>
<script src="/resources/lib/script/datepicker/bootstrap-datetimepicker.min.js"></script>
<script>
    $('.datetimepicker').datetimepicker({
        locale : moment().locale('zh-cn')
    });
</script>
</html>