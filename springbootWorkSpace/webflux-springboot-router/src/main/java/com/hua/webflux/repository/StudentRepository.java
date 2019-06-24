package com.hua.webflux.repository;

import com.hua.webflux.bean.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * company: www.kaikeba.com
 * Author: Rey
 */
// 第一个泛型是该Repository操作的对象类型
// 第二个泛型为操作对象主键的类型
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    /**
     *  根据年龄上下限查询
     * @param below 年龄下限(不包含此边界点)
     * @param top 年龄上限(不包含此边界点)
     * @return
     */
    Flux<Student>  findByAgeBetween(int below, int top);

    /**
     *  使用MongoDB原始查询实现根据年龄上下限查询
     * @param below
     * @param top
     * @return
     */
    // {age:{$gte:21, $lt:25}}
    @Query("{'age':{'$gte':?0, '$lt':?1}}")
    Flux<Student> queryByAge(int below, int top);
}
