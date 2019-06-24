<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    // 创建EventSource对象，指定事件源为sse/default请求
    var es = new EventSource("sse/data");
    // 当接收到服务端发送来的事件时触发该方法执行
    es.onmessage = function (evt) {
        // 参数一：日志名称，随意
        // 参数二：事件中携带的数据
        // 参数三：事件本身
        console.log("默认的时间数据", evt.data, evt);
        // 以下语句可以结束循环
        if(evt.data == 9) {
            es.close();
        }
    }
</script>
<body>

</body>
</html>
