package com.hua.webflux.respsitory;

import com.hua.webflux.entity.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Student 反应式 数据层
 */
public interface StudentReaciveRespsitory extends ReactiveMongoRepository<Student,String> {

    /**
     * 年龄 大于等于 min 且 小于 等于 max
     * @param min
     * @param max
     * @return
     */
    Flux<Student> findByAgeBetween(int min,int max);

    /**
     * 根据名字模糊查询
     * @param name
     * @return
     */
    Flux<Student> findByNameLike(String name);

    /**
     * https://www.runoob.com/mongodb/mongodb-query.html
     * 使用 noSQL 查询 年龄区间
     * @param min
     * @param max
     * @return
     */
    @Query("{age:{$gte:?0,$lte:?1}}")
    Flux<Student> findByAgeSql(int min, int max);
}
