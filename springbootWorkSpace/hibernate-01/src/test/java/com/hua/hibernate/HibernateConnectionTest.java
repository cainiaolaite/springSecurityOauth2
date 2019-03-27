package com.hua.hibernate;

import com.hua.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * hibernate连接测试
 */
public class HibernateConnectionTest {

    //SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
    private SessionFactory sessionFactory=null;
    private static final String HIBERANTE_CONFIG_FILE="hibernate-configuration.xml";

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
    }

    @Test
    public void saveUser(){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        transaction.begin();
        User user=new User();
        user.setId("000000000000000000000");
        user.setUserName("hahha");
        user.setPassword("hahha");
        user.setEmail("1308189967@qq.com");
        user.setPhone("13476096121");
        user.setAddress("武汉");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        session.save(user);
        transaction.commit();
        session.close();
    }


    @After
    public void after(){

    }

}
