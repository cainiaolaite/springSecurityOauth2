package com.hua.webflux.stream;

import com.hua.webflux.Student;

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
@WebServlet("/processor")
public class ProcessorStreamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubmissionPublisher<Student> publisher=new SubmissionPublisher();
        ComstomSubscriber subscriber=new ComstomSubscriber();
        ComstomProcessor comstomProcessor=new ComstomProcessor();
        comstomProcessor.subscribe(subscriber);
        //设置订阅者
        publisher.subscribe(comstomProcessor);
        for(int i=0;i<30;i++){
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Student student=new Student("张"+i);
            publisher.submit(student);
            System.out.println("发布者："+student.toString());
        }
        System.out.println("发布者：发布完毕");
        //关闭发布者
        publisher.close();
    }
}
