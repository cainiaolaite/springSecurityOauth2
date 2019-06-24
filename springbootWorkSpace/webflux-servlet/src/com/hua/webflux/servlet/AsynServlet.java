package com.hua.webflux.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 异步servlet
 *
 * servlet 不会阻塞
 */
@WebServlet(value="/asyn",asyncSupported=true)
public class AsynServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime=System.currentTimeMillis();
        //取得异步servlet 异步内容
        AsyncContext asyncContext=req.startAsync();
        //取得异步servlet的请求
        ServletRequest servletRequest=asyncContext.getRequest();
        //取得异步servlet的响应
        ServletResponse servletResponse=asyncContext.getResponse();
        //异步线程处理业务
        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                servletResponse.getWriter().println("async yibu servlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //complete 完成   完成异步操作
        asyncContext.complete();
        long endTime=System.currentTimeMillis();
        System.out.println("异步 servlet servlet 耗时时间"+(endTime-startTime));
    }
}
