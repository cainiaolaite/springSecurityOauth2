<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    // 创建EventSource对象，指定事件源为sse/custom请求
    var es = new EventSource("sse/event");
    // 当接收到服务端发送来的事件时触发该方法执行
    es.addEventListener("test", function (evt) {
        console.log("事件数据", evt.data);
        // 以下语句可以结束循环
        if(evt.data == 9) {
            es.close();
        }
    });

</script>
<body>

</body>
</html>
