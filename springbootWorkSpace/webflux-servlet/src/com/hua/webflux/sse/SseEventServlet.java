package com.hua.webflux.sse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Sse Servlet
 * Sse 服务器可以多次请求客户端
 */
@WebServlet("/sse/event")
public class SseEventServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/event-stream");//sse 协议内容类型
        resp.setCharacterEncoding("utf-8");//see 协议指定内容编码
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PrintWriter printWriter=resp.getWriter();
            StringBuffer stringBuffer=new StringBuffer();
            stringBuffer.append("\r\n");//协议之间要用 \r\n 隔开
            stringBuffer.append("data:test");
            stringBuffer.append("event:test");
            stringBuffer.append("\n");// \n用户隔绝每个属性
            stringBuffer.append("id:"+i);
            printWriter.println(stringBuffer.toString());
            printWriter.flush();
        }
    }
}
