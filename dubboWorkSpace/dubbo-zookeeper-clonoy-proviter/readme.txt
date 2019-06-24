2.6.0 之后的版本就进入到apache孵化器 http://dubbo.apache.org/zh-cn/
1.错误
    “KeeperErrorCode = ConnectionLoss for /test”
    记得关闭防火墙
    https://www.cnblogs.com/zjiacun/p/7654894.html

2.没有注册中心的dubbo 消费者直接调用提供者
    dubbo-no-register-common

3.有注册中心(zookeeper)没有集群的dubbo
    dubbo-zookeeper-no-clonoy-common

4.有注册中心(zookeeper)有集群的dubbo
    dubbo-zookeeper-clonoy-common

    4.1  消费者配置缓存  （类级别）
          cache="true"
    4.2  方法级别缓存
          <dubbo:method name="sreachUser" cache="lru"></dubbo:method>
         缓存类型
            lru
            threadLocal
            jcache
    4.3  根据服务版本调用服务
            provider 提供不同版本的 服务实现
                <dubbo:service
                            interface="com.hua.dubbo.service.DubboBaseService"
                            ref="dubboBaseServiceChangeImpl"
                            version="0.0.2"
                    ></dubbo:service>
            consumer 调用不同版本的 服务
                <dubbo:reference
                            id="dubboBaseService"
                            interface="com.hua.dubbo.service.DubboBaseService"
                            version="0.0.2"
                    >
                </dubbo:reference>
    4.4 服务分组
            provider 提供服务时，对服务分组了
                <dubbo:service
                            interface="com.hua.dubbo.service.DubboBaseService"
                            ref="dubboBaseServiceChangeImpl"
                            group="user"
                    ></dubbo:service>
            consumer 比如用户分类 用户组 就关于用户的 所有操作
                <dubbo:reference
                            id="dubboBaseService"
                            interface="com.hua.dubbo.service.DubboBaseService"
                            group="user"
                    >
                </dubbo:reference>


    4.5 延时注册服务
            服务提供者 启动后，服务不会立马注册，而是等过了延时期，才注册服务
            <!--
                delay="2000"
                服务延时注册 ，单位毫秒
            -->
            <dubbo:service
                    interface="com.hua.dubbo.service.DubboBaseService"
                    ref="dubboBaseServiceChangeImpl"
                    version="0.0.2"
                    delay="2000"
            ></dubbo:service>

    5.多注册中心
        1.同一个服务注册到多个中心
        2.不同服务注册到不同中心
        3.同一个服务引用不同的中心

    6.多协议支持
        dubbo 框架致辞的其中服务协议
            Hessian 协议
            HTTP协议
            RMI 协议
            WebService 协议
            Thrift 协议
            Memcached 协议
            Redis 协议
            Dubbo 服务暴露协议（默认，使用最多）

        Dubbo 的高级设置（注册服务的时候）
            timeout:远程服务调用超时实现
            retries:失败充实次数，默认值是2
            loadalance:负载均衡算法，默认是随机random。（轮询roundrobin,不活跃有限leastactive）
            actives:消费者最大并发调用限制，即当Consumer 对一个服务的并发调用到
            上限，新调用会阻塞直到超时。

5.spring boot 应用dubbo
    dubbo-springboot

    @EnableDubboConfiguration  和  @EnableDubboConfig  和 @EnableDubbo 区别 ?

    @EnableDubboConfiguration 是 autoconfigure jar下的配置
        趋近于自动化
        配置的开头是
                    spring.dubbo.registry.address
    @EnableDubboConfig        是 dubbo-spring-boot-starter jar 下的配置
        趋近于自动化
        配置的开头是
            spring.dubbo.registry.address
    @EnableDubbo              是 duboo 下的配置
        配置的开头是
            dubbo.registry.address

    三者都可以，但 @EnableDubboConfig 更接近于 springboot

    注：dubbo-springboot-provider 在idea下启动不了（tomcat 一直宕机）,通过 java -jar **.jar 可以正常启动。