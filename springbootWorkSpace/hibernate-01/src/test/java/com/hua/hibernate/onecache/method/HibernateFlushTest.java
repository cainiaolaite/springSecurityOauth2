package com.hua.hibernate.onecache.method;

import com.hua.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Hibernate 的一级缓存-flush测试
 * Hibernate 的一级缓存就是Session 的缓存
 */
public class HibernateFlushTest {
    //SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
    private SessionFactory sessionFactory=null;
    private static final String HIBERANTE_CONFIG_FILE="hibernate-configuration.xml";
    private Session session;
    private Transaction transaction;
    @Before
    public void before(){
        /**
         * 分析： 需要构建SessionFactory   SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
         *          需要  Mapping(映射文件)  SessionFactoryOptions （参数）
         *
         *        SessionFactoryBuilderImpl  Session 工厂的建造者模式
         */
        //1.通过配置文件获取
        Configuration configuration=new Configuration().configure(HIBERANTE_CONFIG_FILE);//实例化它需要这个对象  默认的参数 hibernate.cfg.xml
        ServiceRegistry serviceRegistry=configuration.getStandardServiceRegistryBuilder().build();
        MetadataSources metadataSources=new MetadataSources(serviceRegistry);
        sessionFactory=metadataSources.buildMetadata().buildSessionFactory();
        session=sessionFactory.openSession();
        transaction=session.getTransaction();
        transaction.begin();
    }

    @Test
    public void saveUser(){
        User user=session.get(User.class,"000000000000000000000");
        System.out.println("第一次从数据库中取出id为【000000000000000000000】:"+user.toString());
        System.out.println("更新userName为【--wuhaihua--】");
        user.setUserName("--wuhaihua--");
        session.flush();
        System.out.println("执行了session.flush()后，从新获取数据");
        User user2=session.get(User.class,"000000000000000000000");
        System.out.println("第二次次从数据库中取出id为【000000000000000000000】:"+user2.toString());
        System.out.println("如日志所示：session.flush() 如果缓存的数据与数据库中的数据不一致，就更新数据库，\n1.使用flush需要开启事务 \n2.使数据库数据与缓存保持一致（无论你缓存变了数据库也要跟着变）");
    }


    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
