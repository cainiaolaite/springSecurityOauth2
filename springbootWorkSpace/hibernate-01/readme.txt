Session接口
Session接口是Hibernate向应用程序提供的操纵数据库的最主要的接口，它提供了基本的保存，更新
删除和查询的方法。

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



