package com.hua.webflux.stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.SubmissionPublisher;

/**
 * 流编程
 * 订阅者 和 发布者
 */
@WebServlet("/stream")
public class StreamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubmissionPublisher<String> publisher=new SubmissionPublisher();
        ComstomSubscriber subscriber=new ComstomSubscriber();
        //设置订阅者
        publisher.subscribe(subscriber);
        for(int i=0;i<30;i++){
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publisher.submit(i+"");
            System.out.println("发布者："+i);
        }
        System.out.println("发布者：发布完毕");
        //关闭发布者
        publisher.close();
    }
}
