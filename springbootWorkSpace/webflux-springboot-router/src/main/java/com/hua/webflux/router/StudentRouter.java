package com.hua.webflux.router;

import com.hua.webflux.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * company: www.kaikeba.com
 * Author: Rey
 */
@Configuration  // 表示该类为CodeConfig类，其充当Spring容器
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> customRouter(StudentHandler handler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/student"),
                        RouterFunctions.route(RequestPredicates.GET("/all"), handler::findAllHandle)
                                        .andRoute(RequestPredicates.POST("/save")
                                                                    .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                                  handler::saveHandle)
                                        .andRoute(RequestPredicates.DELETE("/del/{id}"), handler::delHandle)
                                        .andRoute(RequestPredicates.PUT("/update/{id}"), handler::updateHandle));
    }
}
