package com.hua.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HqlBatchAddDeleteChangeTest {

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
    public void batchInsert(){
        /*String hql = "insert into Student(name,sex) values (?,?)";
        int result=session.createQuery(hql).setParameter(0,"张三").setParameter(1,"男").executeUpdate();
        System.out.println(result);*/
    }

    /**
     * 批量更新
     */
    @Test
    public void batchUpdate(){
        String hql="update Student t set t.name=:name where t.sex=:sex";
        int result=session.createQuery(hql).setParameter("name","张三").setParameter("sex","男").executeUpdate();
        System.out.println(result);
    }

    /**
     * 批量删除
     */
    @Test
    public void batchDelete(){
        String hql="delete Student t where t.name=:name";
        int result=session.createQuery(hql).setParameter("name","张三").executeUpdate();
        System.out.println(result);
    }


    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
