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

11.读取自定义配置
    1.自定义属性文件引用注解
        @PropertySource()
    2.自定义属性类型
        读取文件中的属性
            @Value
        自定义对象属性
            @ConfigurationProperties
        自定义List<String>属性
        自定义List<Object>属性

    注：插件lombok （自动添加get,set,hasCode,toString等方法）
            1.添加插件
            2.添加maven 依赖
            3.类上 添加@Data
    例：AutoPropertiesController

12.Spring Boot 下使用JSP页面
    1.在pom文件中注册JSP解析器依赖
        1.添加webapp资源目录
            Project Structure(窗口)--》选中模块--》选中Web
            --》Web Resource Directories--》点加--》选择src/main/webapp(输入webapp)
            注：项目下会生成 webapp
        2.maven仓库中添加  tomcat-embed-jasper 依赖
            <!-- https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper -->
            <dependency>
                <groupId>org.apache.tomcat.embed</groupId>
                <artifactId>tomcat-embed-jasper</artifactId>
                <version>9.0.19</version>
            </dependency>

    2.在pom文件中注册资源目录
        1.pom.xml 文件中 注册 包下 mybatis 映射文件资源目录 (没有mybatis 可以省略)
        2.pom.xml 文件中 注册webapp 目录为资源目录
           <build>
                <resources>
                    <!--注册 包下 mybatis 映射文件资源目录-->
                    <resource>
                        <directory>src/main/java</directory>
                        <includes>
                            <include>**/*.xml</include>
                        </includes>
                    </resource>

                    <!--注册webapp 目录为资源目录-->
                    <resource>
                        <directory>src/main/webapp</directory>
                        <targetPath>META-INF/resources</targetPath>
                        <includes>
                            <include>**/*.*</include>
                        </includes>
                    </resource>
                </resources>
           </build>
    注：
    例:

13.spring data jpa
    1.添加 Mysql 连接
        1.pom文件添加依赖
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.46</version>
            </dependency>
        2.properties 文件设置
            spring.datasource.driver-class-name=com.mysql.jdbc.Driver
            spring.datasource.url=jdbc:mysql://localhost:3306/dev
            spring.datasource.username=root
            spring.datasource.password=ceba

    2.添加 alibaba druid（德鲁伊） 数据源连接池
        1.pom文件添加依赖
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.16</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>1.1.9</version>
            </dependency>
        2.数据库连接池设置
            spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
            spring.datasource.name=druidDataSource
        3.德鲁伊设置
            #属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall#
            spring.datasource.druid.filters=stat,wall,slf4j,config
            #最大连接池数量#
            spring.datasource.druid.max-active=100
            #初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时#
            spring.datasource.druid.initial-size=1
            #获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。#
            spring.datasource.druid.max-wait=60000
            #最小连接池数量#
            spring.datasource.druid.min-idle=1
            #有两个含义：1) Destroy线程会检测连接的间隔时间2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明#
            spring.datasource.druid.time-between-eviction-runs-millis=60000
            spring.datasource.druid.min-evictable-idle-time-millis=300000
            #用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
            spring.datasource.druid.validation-query=select 'x'
            #建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
            spring.datasource.druid.test-while-idle=true
            #申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
            spring.datasource.druid.test-on-borrow=false
            #归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
            spring.datasource.druid.test-on-return=false
            #是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
            spring.datasource.druid.pool-prepared-statements=false
            #要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
            spring.datasource.druid.max-open-prepared-statements=50
            spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
            #--访问监控页面配置-#
            spring.datasource.druid.web-stat-filter.login-username=hua
            spring.datasource.druid.web-stat-filter.login-password=hua
            spring.datasource.druid.web-stat-filter.url-pattern=/druid/*

    4.开启 jpa 二级缓存
          #show-sql=true 看的见sql 语句
          spring.jpa.show-sql=true
          #输出的sql语句 被格式化#
          spring.jpa.properties.hibernate.format_sql=true
          #database=mysql mysql 数据源
          spring.jpa.database=mysql
          spring.data.jpa.repositories.enabled=true
          spring.data.jpa.repositories.bootstrap-mode=lazy
          #update : 第一次启动hibernate 会帮助创建表，以后启动 如果 entity 有更新 那么 表就会跟着自动更新
          spring.jpa.hibernate.ddl-auto=update

          #jpa 开启二级缓存
          spring.jpa.properties.hibernate.cache.use_second_level_cache=true
          spring.jpa.properties.cache.use_query_cache=true
          #指定缓存provider
          spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
          spring.jpa.properties.hibernate.cache.provider_configuration_file_resource_path=ehcache.xml
    4.添加 ehcache 缓存
        1.pom文件添加依赖
            <!--jpa 开启二级缓存-->
            <dependency>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-ehcache</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-cache</artifactId>
            </dependency>
    6.添加slf4j->logback 日志
        1.pom文件添加依赖 （德鲁伊的日志是 slf4j）
            <!--logback-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </dependency>
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-access</artifactId>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
            </dependency>





14.spring boot 开启事务
    在启动类里加注解
        @EnableTransactionManagement