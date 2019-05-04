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

public class HibernateGetLoadTest {

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
    public void getTest(){
        User user=session.get(User.class,"000000000000000000000");
        //com.hua.hibernate.entity.User
        System.out.println(user.getClass().getName());
        //特点是 立即答应sql
    }

    @Test
    public void loadTest(){
        User user=session.load(User.class,"000000000000000000000");
        //com.hua.hibernate.entity.User_$$_jvst27b_0
        System.out.println(user.getClass().getName());
        //还没有答应sql

    }

    @Test
    public void load1Test(){
        User user=session.load(User.class,"000000000000000000000");
        //com.hua.hibernate.entity.User_$$_jvst27b_0
        System.out.println(user.getClass().getName());
        //还没有答应sql
        //session.close();   加了就报错，应为代理在使用的时候，session 已经关闭了
        user.toString();
    }


    @After
    public void after(){
        session.close();
        sessionFactory.close();
    }
}
