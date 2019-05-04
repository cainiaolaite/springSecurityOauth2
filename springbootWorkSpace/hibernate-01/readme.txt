Session接口
Session接口是Hibernate向应用程序提供的操纵数据库的最主要的接口，它提供了基本的保存，更新
删除和查询的方法。

一。hibernate 一级缓存
Session是有一个缓存，又叫Hibernate的一级缓存
session缓存是由一系列的Java集合构成的。
当一个对象被加入到Session缓存中，这个对象的引用就加入到了Java的集合中，
以后即使应用程序中的引用变量不在引用该对象，
只要Session缓存不被清空，这个对象一直处于生命周期中。

Session缓存的作用：
1.减少访问数据库的频率
2.保证缓存中的对象与数据库中的先关记录保持同步

Session 清理缓存的时机
1.当调用Transaction 的 commit() 方法时，commit()方法先
清理缓存（前提是FlushMode.COMMIT/AUTO）,然后再向数据库提交事务。
2.当应用程序调用Session的find()或者iterate()等查询数据的方法时，
如果缓存中的持久化对象的属性发生了变化，就会先清理缓存，
一保证查询结果能反应持久化对象的最新状态
3.当应用程序显示调用Session的flush()方法的时候。
hibernate  清理模式
    1.FlushMode.AUTO
        session 的查询方法       清理
        session 的commit()方法   清理
        session 的 flush()方法   清理
    2.FlushMode.COMMIT
        session 的查询方法       不清理
        session 的commit()方法   清理
        session 的 flush()方法   清理
    3.FlushMode.NEVER
        session 的查询方法       不清理
        session 的commit()方法   不清理
        session 的 flush()方法   不清理


Hibernate处理的对象在不同的程序执行阶段存在不同的状态：
我们知道Hibernate的核心就是对数据库的操作，里面的核心
接口就是org.hibernate.Session接口。要想对数据库操作
我们就要理清楚Hibernate里的对象在整个操作中的所属的状态
（主要有三个：临时，持久，游离）

Transaction transaction=session.getTransaction();
transaction.begin();
---------------------临时状态------------------
User user=new User();
---------------------临时状态------------------
session.save(user);
---------------------持久化状态------------------
transaction.commit();
---------------------游离状态------------------
session.close();
---------------------游离状态------------------


持久化状态：数据即存在于缓存中，有存在于数据库中
游离状态：
------------------------------------

总结：
1.对于刚创建的一个对象，如果session中和数据库中都不存在该对象
那么该对象就是瞬时对象（Transient）
    瞬时对象：不存在数据库中，session缓存中
2.瞬时对象调用save方法，或者你先对象调用update方法可以使该对象
变成持久化对象，如果对象是持久化对象时，那么对该对象的任何修改
都会在提交事务时才会与之进行比较，如果不同，则发送一条update语
句，否则就不会发送语句
    持久对象：即存在于数据库中，又存在于session缓存中
3.离线对象就是，数据库存在该对象，但是该对象有没有被session所
托管。
    离线对象：存在于数据库中，不存在与session缓存中

例如
    开启事务
        session 执行 get,update,load,iterate,save
        对象就会被持久化
        持久化的对象  缓存对象如果（在关闭事务之前）有更新
        hibernate 会将 缓存对象与 数据库对象做对比，如果有变化就 执行 更新语句。
    关闭事务

    session 执行 get        离线对象--》持久化对象
    session 执行 update     离线对象--》持久化对象
    session 执行 load       离线对象--》持久化对象
    session 执行 iterate    离线对象--》持久化对象
    session 执行 save       瞬时对象--》持久化对象


    持久化对象--》开启事务--》更新属性（对象.set属性）--》flush--》关闭事务  （对象就会被更新到数据库）

    如下：HibernateMethodTest

1.  session.get() 和  session.load() 的区别
    session.get() 马上执行 select 查询语句，取得对象，并持久化
    session.load() 是懒加载，返回的Student的代理，只有当使用的时候才会去执行select 查询语句，取得对象，并持久化



2.session.delete()
    1.删除对象
    2.删除持久对象
    3.删除数据库里对应的记录
    4.当删除的独享数据库里没有对应的id值的记录是抛出异常
    5.默认的删除的时候，会把缓存对象和数据库中记录删除，但这个对象
    会保留id,妨碍后面重复利用这个对象，这个问题通过配置来处理，作用
    删除操作后吧对象的id设置null

    <property name="hibernate.use_identifier_rollback">true</property>

    Hibernate 的 cfg.xml 配置文件中有一个 hibernate.use_identitier_rollback
    属性，其默认值为false，若吧他设为true，将改变delete() 方法的运行行为
    delete() 方法会把持久化对象或有你兑仓过的OID设置为null，使他们变为
    临时对象，这样程序就可以重复利用这些对象了。

3.session.evict 方法
    这个方法就是将持久化对象从session缓存中删除，使其成为一个游离的对象

4.session.doWork方法
    这个方法是在hibernate中拿到jdbc的原生的connection






二。hibernate 映射
    1.一对多
        1.单向 一对多
        2.双向 一对多
        3.双向 多对一
    2.一对一

    3.多对多
        1.单向 多对多
        2.双向 多对多

    4.组件映射
        com.hua.hibernate.oneway.manyone.ManyOneTest
    5.继承映射（三种映射策略）
        public class Animal{
            private int id;
            private String name;
            private String sex;
        }
        public class Pig extends Animal{
            private int weight;
        }
        public class Bird extends Animal{
            private int height;
        }
        1.第一种策略是把三个类，映射到一张数据表上
            优点：查询效率高 缺点 数据冗余，重复
        2.每个类一张表（父类Animal,子类Pig,子类Bird各一张表,父表中有公共字段,字表中有个性字段+外键约束）
            优点：数据结构清晰，不重复 缺点：查询效率低
        3.每个具体类一张表（每个子类一张表，每张表都有自己所有的属性字段包括父类的公共字段）
            优点：数据不冗余 缺点：查询效率低，需要的表多

三。hibernate 检索
    1.立即检索策略
        采用立即检索策略，会将被检索的对象，以及和这个对象
        关联的一对多对象都加载到缓存中。Session的get方法就
        使用的立即检索策略
        优点：频繁使用的关联对象能够被加载到缓存中。
        缺点：1.占用内存  2.select语句过多

    2.延迟检索策略
        采用延迟检索策略，就不会加载关联对象的内容。知道第一次
        调用关联对象时，才去加载关联对象。在不涉及关联类操作时
        延迟检索策略只适用于Session的load方法。涉及关联类操作
        时，延迟检索策略也能够使用于get，list等操作。

        在类级别操作时（也就是只涉及一张表时），延迟检索策略，
        只加载类的OID不加载类的其他属性，只用当第一次访问其他属性
        时，才会访问数据库取加载内容

        在关联级别操作时（也就是有一对多，多对多...关联关系时，涉及
        多张表时），延迟检索策略，只加载类本身，不加载关联类，直到
        第一次调用关联对象时，才去加载关联对象 程序的默认模式都是用
        延迟加载策略。如果需要制定使用延迟加载策略。在配置文件中设置
        <class>的lazy=true,
        <set>的lazy=true 或 extra (增强延迟)
        <many-to-one> 的lazy=proxy 和 no-proxy  (代理就是延迟加载)

        优点：由程序决定加载那些类和内容，避免了大量无用的sql语句和
        内存消耗
        缺点：在session关闭后，就不能访问关联类对象了。强行访问就会
        发生懒加载异常，所以需要确保在Session.close方法前，调用关联
        对象。

    3.左外连接检索策略
        采用左外连接检索，能够使用Sql的外链接查询，将需要加载的关联
        加载在缓存中。
            <set>fetch设置为join,<many-to-one>的fetch设置为join

         fetch除join取值外，还有select和subselect的取值，它们决定了
         发起的sql语句的形式，分开独立的select查询，还是子查询，还是
         外连接查询。

         优点：1.对应用程序完全透明，不管对象处于持久化状态，还是游离
         状态，应用程序都可以方便的从一个对象导航到与它关联的对象。
         2.使用了外连接，select语句数目少。

         缺点：1.可能会加载应用程序不需要访问的对象，白白浪费许多内存
         空间。 2.复杂的数据库连接也会影响检索性能。

         batch-size 属性  （关联数量）
         无论是立即检索还是延迟检索，都可以指定关联查询的数量，这就需要
         使用batch-size 属性来指定，指定关联查询数量，以减少批量检索的
         数据数目。从而提高检索的性能。

         注：取值是join 时，不能喝batch-size 属性一起用，加载一这段对象
         时，它会用左外连接将关联对象一起加载进来！而且这时lazy属性没有
         作用了  2 hql查询 fetch=join 是没有作用  （join 关联一次性查询出来）


四。Hibernate的HQL （面向对象的查询sql,sql 里只有 对象，属性 （没有表，字段属性））
    1.HQL 查询

    2.HQL 映射文件

    3.HQL 查询对象的部分字段

    4.HQL 函数聚合

    4.HQL 连接查询
        内连接   inner join 或 join
        迫切内连接  inner join fetch 或 join fetch
        左外连接    left outer join 或 left join
        迫切左外连接 left outer join fetch 或 left join fetch
        右外连接     right outer join 或 right join

    5.HQL 批量增删改

五。Hibernate的QBC
    HibernateQBCTest

六。Hibernate的二级缓存
    二级缓存是在SessionFactory ,所有的Session共享同一个二级cache。二级Cache的内部
    如何实现并不重要，重要的是采用哪种正确的缓存策略，以及采用那个Cache提供器。

    二级缓存分为两种
        内置缓存：Hibernate自带的，不可卸载，通常在Hibernate的初始化阶段，Hibernate
        会把映射元数据和提前定义的SQL语句防止到SessionFactory的缓存中。改内置缓存是
        仅仅读的

        外置缓存：通常说的二级缓存也就是外置缓存，在默认情况下SessionFactory不会启用
        这个缓存插件，外置缓存中的数据是数据库数据的复制，外置缓存的物理介质能够是
        内存或者硬盘

    二级缓存并发访问策略
        transactional(事务型)
            缓存支持事务，发生异常的时候。缓存也可以回滚
            仅在受管理的环境中使用
            提供Repeatable Read事务隔离级别
            适用常常被读。非常少改动的数据
            能够防止脏读和不可反复读的并发问题

        read-write (读写型)
            更新缓存的时候锁定缓存中的数据
            提供Read Committed 事务隔离级别
            在非集群的环境中适用
            适用常常被读，非常少改动的数据
            能够防止脏读

        nonstrict-read-write(非严格读写型)
            适用极少被改动，偶尔同意脏读的数据（两个事务同一时候改动数据的情况非常少见）
            不保证缓存和数据库中数据的一致性
            为缓存数据设置非常短的过期时间，从而尽量避免脏读
            不锁定缓存中的数据

        read-only(仅仅读型)
            适用从来不会被改动的数据（如参考数据）
            在此模式下，加入对数据进行更新操作，会有异常发生
            事务隔离级别第，并发性高。

    二级缓存的实现
        1.类缓存
            针对类的对象的缓存
                <class-cache class="com.hua.hibernate.hql.Student" usage="read-write"></class-cache>

        2.集合缓存
            针对于类的对象属性集合
                <collection-cache collection="com.hua.hibernate.towway.manymany.Role.permissionSet" usage="read-write"></collection-cache>

        3.ehcache 缓存块 hibernate 应用
            ehcache 缓存的讲解
                <diskStore path="F:/wuhaihuaSpace/ehcache" />
                    指定一个文件目录，当EhCache把数据写到硬盘上时，将把数据写到这个文件目录下

                <defaultCache>
                    默认的缓存

                <cache>
                    指定对象的缓存配置，其中 name 属性为指定缓存的名称（必须唯一）

                属性
                    maxElementsInMemory (正整数)
                        在内存中缓存的最大对象数量
                    maxElementsOnDisk (正整数)
                        在磁盘上缓存的最大对象数量，默认值为0，表示不限制
                    eternal
                        设定缓存对象保存的永久属性，默认为false
                        为true 时：timeToldleSeconds,timeToLiveSeconds 失效。表示这个缓存永远不清除
                    timeToIdleSeconds（单位：秒）
                        对象空闲时间，指对象在多长时间没有被访问就会失效。
                        只有eternal 为false的有效。默认值0，表示一直可以访问。失效时间！
                    timeToLiveSeconds（单位：秒）
                        对象存活时间，指对象从创建失效所需要的时间。
                        只对eternal为false的有效。默认值0，表示一直可以访问
                    overflowToDisk
                        如果内存中数据超过内存限制，是否要缓存到磁盘上。
                    diskPersistent
                        是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false.
                    diskSpoolBufferSizeMB (单位：MB)
                        DiskStore 使用的磁盘大小，默认为30MB。每个cache 使用各自的 diskStore.
                    memoryStoreEvictionPolicy
                        如果内存中数据超过内存限制，向磁盘缓存时的策略。默认值LRU，可选FIFO,LFU
                        LRU
                            先进先出
                        FIFO
                            最少被使用
                            缓存的元素有一个hit属性，hit值最小的将会被清除缓存
                        LFU
                            最近最少使用
                            缓存的元素有一个时间戳，当缓存容量满了，而有需要腾出对方来缓存新的元素的时候
                            那么现有缓存元素中时间戳离当前时间最远的元素将被清除

        4.hibernate 查询缓存开启

        5.hibernateUtils 实现



