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

import java.util.Set;

/**
 * 多对多延迟增强
 */
public class ManyManyLazyExtra {
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
        Group student=new Group();
        student.setName("学生会");

        Member zhang=new Member();
        zhang.setName("章");

        Member li=new Member();
        li.setName("李");

        student.getMemberSet().add(zhang);
        student.getMemberSet().add(li);

        session.save(li);
        session.save(zhang);
        session.save(student);
    }

    /**
     * 延迟增强
     * <set name="memberSet" table="lazy_group_member" lazy="extra">
     */
    @Test
    public void testLazyExtra(){
        Group group=session.get(Group.class,1);
        Set<Member> memberSet=group.getMemberSet();
    }

    /**
     * 延迟增强
     * <set name="memberSet" table="lazy_group_member" lazy="extra">
     * member 集合 中只要有一个对象未被用到 就不会查询
     */
    @Test
    public void testLazyExtra1(){
        Group group=session.get(Group.class,1);
        Set<Member> memberSet=group.getMemberSet();
        if(!memberSet.isEmpty()){
           System.out.println(memberSet.iterator().next().getName());
        }
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
