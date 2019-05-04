package com.hua.hibernate.towcache;

import com.hua.hibernate.hql.Student;
import com.hua.hibernate.hql.Teachar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 二级缓存中的  类缓存
 */
public class TowcacheClassCacheTest {
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



    /**
     * 分页查询 Hql 语句映射到文件中
     */
    @Test
    public void testTowCache(){
        transaction=session.getTransaction();
        transaction.begin();
        Student student=session.get(Student.class,2);
        System.out.println(student.toString());
        transaction.commit();
        session.close();
        Session session1=sessionFactory.openSession();
        Student student1=session1.get(Student.class,2);
        System.out.println(student1.toString());
    }


    @After
    public void after(){
        sessionFactory.close();
    }
}
