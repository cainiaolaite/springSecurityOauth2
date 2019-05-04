package com.hua.hibernate.retrieval.lazy;

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
 * 多对一延迟代理
 */
public class ManyOneLazyProxy {

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
    public void testSave(){
        Student student=new Student();
        student.setName("黄冈学生");
        Student student1=new Student();
        student.setName("溪水学生");
        Teachar teachar=new Teachar();
        teachar.setName("湖北教师");
        student.setTeachar(teachar);
        session.save(student);
        session.save(student1);
        session.save(teachar);
    }

    /**
     * 多对一延迟代理
     * <many-to-one name="teachar" class="Teachar" lazy="proxy">
     */
    @Test
    public void lazyProxy(){
        Student student=session.get(Student.class,1);
        System.out.println("看 hibernate 日志 "+student.getName());
    }

    /**
     * 多对一延迟代理
     * <many-to-one name="teachar" class="Teachar" lazy="proxy">
     */
    @Test
    public void lazyProxy1(){
        Student student=session.get(Student.class,1);
        System.out.println("看 hibernate 日志 "+student.getTeachar().getName());
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
