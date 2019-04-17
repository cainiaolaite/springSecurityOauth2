package com.hua.hibernate.retrieval.promptly;

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
 * 1.立即检索策略
 采用立即检索策略，会将被检索的对象，以及和这个对象
 关联的一对多对象都加载到缓存中。Session的get方法就
 使用的立即检索策略
 优点：频繁使用的关联对象能够被加载到缓存中。
 缺点：1.占用内存  2.select语句过多
 */
public class PromptlyTest {
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
     * session.get() 是立即检索策略，<class lazy="true"> lazy 属性影响不了它
     */
    @Test
    public void testGetLazy(){
        Student student=session.get(Student.class,1);
        System.out.println(student.getId());
    }

    /**
     *  load 为延迟检索策略  仅在lazy=true <class lazy="true">
     *      lazy=false 效果跟get是一样的
     */
    @Test
    public void testLoadLazy(){
        Student student=session.load(Student.class,1);
        System.out.println(student.getId());
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
