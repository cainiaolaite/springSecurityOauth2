1.mvn（目录结构） 的作用
    .mvn
        -- 不同版本的maven 都会有不同 的插件支持，有的高版本的插件
           无法 在低版本上使用，所以必须的降低maven版本来解决此问题
           mvnw 就是解决这一问题的（spring 构建项目的时候回自带）

2.@SpringBootApplication 注解 的内部注解
    @SpringBootApplication
        @SpringBootConfiguration
            @Configuration(可以配置bean)
        @EnableAutoConfiguration(自动配置)
        @ComponentScan(扫描包)
    注. 加了 SpringBootApplication 注解的 就可以 直接配置（使用@Bean）,内部包含@Configuration

3.maven  的选择性继承   和   直接继承
    dependencies 直接继承
    dependencyManagement 选择性依赖

4.maven 执行阶段 package  和   repackage
    package (打包)
    repackage(重新打包)

5 primary-01-0.0.1-SNAPSHOT.jar  和  primary-01-0.0.1-SNAPSHOT.jar.original（原始的） 的关系
    1.项目模块primary-01 正常命令（package） 生成  primary-01-0.0.1-SNAPSHOT.jar （没有主类，没有spring boot jar）
    2.repackage 将 原有的包 加上了 original，再次重新打包，并且加上 主类 和 spring boot 的jar

6.热部署
    1.导入依赖
        <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    2.设置钝化（窗口切换的时候） ，重启spring boot工程
         Run/Debug 窗口中，springboot 应用下 项目
         On frme deactivation （在窗口钝化的时候）: update classes and resources （更新代码和资源）

7.配置文件  properties 和 yml
    properties 标记语言（以标记为中心）
    yml        非标记语言 （数据为中心）


8.Actuator  (应用程序监控的功能)
    每个模块中都可以引用，类似于 doubo 的数据监控中心

    1.添加依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    2.数据监控的种类
        info 自动义信息
        padddings 控制器信息
        ...
    3.看配置文件中 management 配置（他代表着数据监控）

9.错误页面处理
   404  500等
   1.在 resources中新建 目录
       resources
           --public
                --error
                     -- 404.html
                     -- 500.html


10.多环境选择
    开发过程中  项目代码 在不同的环境中（开发，测试，生成），配置，代码会有所不同，如果每次更换环境都需要更换代码
    那么很麻烦，spring boot 解决了这一问题。

    1.配置不同环境的 配置文件
        resources
           --application.properties  (主配置文件)
           --application-dev.properties  (开发环境配置文件)
           --application-test.properties (测试环境配置文件)
           --application-pro.properties  (生产环境配置文件)
        spring.profiles.active=dev （选择的环境）
    2.实现不同环境的代码
        不同环境下 接口代码的实现 需要加这个注解
        @Profile("dev")
    3.启动jar时 动态控制环境变量的 参数
        java -jar primary-01-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev  （启动时选择的环境）