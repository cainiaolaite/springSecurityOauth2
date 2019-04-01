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
 * Hibernate 的一级缓存-refresh测试
 * Hibernate 的一级缓存就是Session 的缓存
 */
public class HibernateRefreshTest {
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
    }

    @Test
    public void saveUser(){
        User user=session.get(User.class,"000000000000000000000");
        System.out.println("第一次从数据库中取出id为【000000000000000000000】:"+user.toString());
        System.out.println("更新userName为【--wuhaihua--】");
        user.setUserName("--wuhaihua--");
        session.refresh(user);
        System.out.println("执行了session.refresh()后，再次看缓存数据:"+user.toString());
        System.out.println("如日志所示：session.refresh() 如果缓存的数据与数据库中的数据不一致，就更新缓存 \n1.使缓存与数据库中保持一致（无论你缓存怎么变都要与数据库保持一致）\n2. refresh的执行相当于重新查询数据库后，重置了缓存。");
    }


    @After
    public void after(){
        session.close();
        sessionFactory.close();
    }
}
