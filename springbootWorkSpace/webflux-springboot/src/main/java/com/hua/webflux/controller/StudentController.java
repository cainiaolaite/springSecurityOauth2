package com.hua.webflux.controller;

import com.hua.webflux.respsitory.StudentReaciveRespsitory;
import com.hua.webflux.entity.Student;
import com.hua.webflux.utils.HibernateValidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

//rest 语法  对象/事件
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentReaciveRespsitory studentReaciveRespsitory;
    /**
     * 学生表单式新增
     *    表单请求 表单中 有 student 的属性
     * @param student
     * @return
     */
    @PutMapping("/save")
    public Mono<Student> save(@Valid Student student){
        HibernateValidUtils.validStudentName(student.getName());
        return studentReaciveRespsitory.save(student);
    }

    /**
     * Mono 是单个  flux 是多个
     * 学生 请求体是 JSON 格式的新增
     * @param student
     * @return
     */
    @PostMapping("/save/json")
    public Mono<Student> saveJson(@RequestBody @Valid Student student){
        HibernateValidUtils.validStudentName(student.getName());
        return studentReaciveRespsitory.save(student);
    }

    /**
     * 无状态删除（根据响应码是看不出来删除成功还是失败）
     * 删除学生
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Mono<Void> noStatusDelete(@PathVariable("id") String id){
        return studentReaciveRespsitory.deleteById(id);
    }

    /**
     * 有状态删除（删除成功 返回200 删除失败（没有发现删除数据，或操作失败）返回 404）
     * ResponseEntity 可以封装响应体中的数据及响应码
     *
     *  Mono 的 map() 与 flatMap() 方法均可用于做元素映射，选择标准是：
     *  1.一般情况下映射过程中需要在执行一些操作的过程，需要选择使用flatMap();
     *      flatMap();  异步转换元素
     *  2.若仅仅是元素的映射，而无需执行一些操作，则选择map();
     *      map();   同步转换元素
     * @param id
     * @return
     */
    @DeleteMapping("/status_delete/{id}")
    public Mono<ResponseEntity<Void>> haveStatusDelete(@PathVariable("id") String id){
        //分析 如果删除成功 那么 数据必须先存在
        //使用 findById 放回 Mono<Student> 但是 实际返回的是 Mono<ResponseEntity<Void>>
        //那么 在 Mono<Student> 映射到 Mono<ResponseEntity<Void>> 过程中 需要完成删除
        //正好符合 使用 flatMap
        /*studentReaciveRespsitory.findById(id).flatMap();*/

        /**非lambda 写法**/
        /*//1.查询删除 返回 Mono<Student>
        Mono<Student> studentMono=studentReaciveRespsitory.findById(id);
        //2.从 Mono<Student> 映射到 ResponseEntity<Void> 做删除操作
        Mono<ResponseEntity<Void>> monoResponseEntity=studentMono.flatMap(new Function<Student, Mono<? extends ResponseEntity<Void>>>() {
            @Override
            public Mono<? extends ResponseEntity<Void>> apply(Student student) {
                *//**有数据的时候执行**//*
                Mono<Void> mono=studentReaciveRespsitory.delete(student);
                //删除是返回 Mono<Void>  但是 返回的是 ResponseEntity<Void>
                //说明删除后 要 默认返回 ResponseEntity<Void>
                //then  返回空的时候
                return mono.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
            }
        });
        //如果发现  Mono<ResponseEntity<Void>> 为空（实际等于Mono<Void>） 那么就有实际默认值 new ResponseEntity<Void>(HttpStatus.NOT_FOUND)
        monoResponseEntity.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));*/

        /**lambda 写法**/
        return studentReaciveRespsitory.findById(id)
                .flatMap(student -> studentReaciveRespsitory.delete(student)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 查询所有的学生
     * @return
     */
    @GetMapping("/all")
    public Flux<Student> all(){
        return studentReaciveRespsitory.findAll();
    }

    /**
     * 通过SSE 逐步返回学生
     * 查询所有的学生
     *
     * 注：produces= MediaType.TEXT_EVENT_STREAM_VALUE 是为了加上 SSE 协议
     * @return
     */
    @GetMapping(value = "/sse/all",produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> sseAll(){
        return studentReaciveRespsitory.findAll();
    }

    /**
     * 有状态查询，查询到结果 返回 200，没有查询到 返回 404
     * 查询id相关的学生
     * @param id
     * @return
     */
    @GetMapping("/byId/{id}")
    public Mono<ResponseEntity<Student>> byId(@PathVariable("id") String id){
        return studentReaciveRespsitory.findById(id)
                .map(student->new ResponseEntity<Student>(student,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
    }


    /**
     * 更新 学生
     * @param id
     * @param student
     * @return
     */
    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Student>> update(@PathVariable("id") String id,@RequestBody @Valid Student student){
        /*return studentReaciveRespsitory.findById(id).flatMap(stu->{
            student.setId(id);
            return studentReaciveRespsitory.save(student);
        }).defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.OK));*/

        /*Mono<Student> studentMono=studentReaciveRespsitory.findById(id);
        Mono<Student> flatMapStudentMono=studentMono.flatMap(new Function<Student, Mono<? extends Student>>() {
            @Override
            public Mono<? extends Student> apply(Student student) {
                student.setId(id);
                return studentReaciveRespsitory.save(student);
            }
        });
        Mono<ResponseEntity<Student>> responseEntityMono=flatMapStudentMono.map(new Function<Student, ResponseEntity<Student>>() {
            @Override
            public ResponseEntity<Student> apply(Student student) {
                return new ResponseEntity<Student>(student,HttpStatus.OK);
            }
        });
         responseEntityMono.defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));*/
        HibernateValidUtils.validStudentName(student.getName());
        return studentReaciveRespsitory.findById(id)
                .flatMap(stu->{
                    student.setId(stu.getId());
                    return studentReaciveRespsitory.save(student);
                })
                .map(_stu->new ResponseEntity<Student>(_stu,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 根据年龄的最大值 和 最小值 查询
     * @param min
     * @param max
     */
    @GetMapping("/age/{min}/{max}")
    public Flux<Student> findByAge(@PathVariable("min") int min, @PathVariable("max") int max){
        return studentReaciveRespsitory.findByAgeBetween(min,max);
    }

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public Flux<Student> likeByName(@PathVariable("name") String name){
        return studentReaciveRespsitory.findByNameLike(name);
    }


    /**
     * 根据年龄的最大值 和 最小值 查询
     * @param min
     * @param max
     */
    @GetMapping(value = "/sse/age/{min}/{max}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> sseFindByAge(@PathVariable("min") int min, @PathVariable("max") int max){
        return studentReaciveRespsitory.findByAgeBetween(min,max);
    }

    /**
     * 使用 noSQL 查询 年龄区间
     * @param min
     * @param max
     * @return
     */
    @GetMapping(value="/sql/sse/age/{min}/{max}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> sqlSseFindByAge(@PathVariable("min") int min,@PathVariable("max") int max){
        return studentReaciveRespsitory.findByAgeSql(min,max);
    }

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    @GetMapping(value="/sse/name/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> sseLikeByName(@PathVariable("name") String name){
        return studentReaciveRespsitory.findByNameLike(name);
    }





}
