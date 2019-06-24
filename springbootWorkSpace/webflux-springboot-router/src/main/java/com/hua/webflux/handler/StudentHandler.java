package com.hua.webflux.handler;

import com.hua.webflux.bean.Student;
import com.hua.webflux.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * company: www.kaikeba.com
 * Author: Rey
 */
@Component
public class StudentHandler {

    @Autowired
    private StudentRepository repository;

    // 查询
    public Mono<ServerResponse> findAllHandle(ServerRequest request) {
        return ServerResponse
                // 指定响应码（返回BodyBuilder的方法称为响应体设置中间方法）
                .ok()
                // 指定请求体中的内容类型
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                // 响应体设置终止方法，构建响应体
                .body(repository.findAll(), Student.class);
    }

    // 添加
    public Mono<ServerResponse> saveHandle(ServerRequest request) {
        // 从请求中获取要添加的数据，并将其封装为指定类型的对象，存放到Mono流中
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(repository.saveAll(studentMono), Student.class);
    }

    // 根据id删除：删除成功返回200，没有找到则返回404
    public Mono<ServerResponse> delHandle(ServerRequest request) {
        // 从请求路径中获取id
        String id = request.pathVariable("id");
        System.out.println("id =============== " + id);
        return repository
                .findById(id)
                .flatMap(stu -> repository.delete(stu)
                                    .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

        // Mono的defaultIfEmpty()与switchIfEmpty()方法对比
        // 1)defaultIfEmpty()中的参数是Mono中的值。若其调用者Mono中没有任何元素，则会将此方法
        // 中的参数作为该调用者Mono中的元素。调用者Mono与返回值Mono是同一个对象。
        // 2)switchIfEmpty()中的参数是一个Mono。若其调用者Mono中没有任何元素，则直接将参数Mono
        // 作为返回值，而摒弃了原来调用者Mono。即调用者Mono与返回值Mono不是同一个对象。
    }

    // 修改
    public Mono<ServerResponse> updateHandle(ServerRequest request) {
        // 从请求中获取要修改的数据，并将其封装为指定类型的对象，存放到Mono流中
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        // 从请求路径中获取id
        String id = request.pathVariable("id");

        return studentMono
                .flatMap(stu -> {
                    stu.setId(id);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(repository.save(stu), Student.class);
                });
    }
}
