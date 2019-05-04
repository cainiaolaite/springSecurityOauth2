package com.hua.hibernate.towcache;

import com.hua.hibernate.hql.Student;
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

public class TowcacheQueryCacheTest {
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
     * 二级缓存没有在开启的情况下
     */
    @Test
    public void closeTowCache(){
        transaction=session.getTransaction();
        transaction.begin();

        String hql="from Student s inner join s.teachar t where t.name=:name";
        Query query=session.createQuery(hql).setParameter("name","浙江教师");
        List<Student> studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        transaction.commit();
        session.close();
    }

    /**
     * 关联查询
     * 内连接
     */
    @Test
    public void openTowCache(){
        transaction=session.getTransaction();
        transaction.begin();

        String hql="from Student s inner join s.teachar t where t.name=:name";
        Query query=session.createQuery(hql).setParameter("name","浙江教师");
        query.setCacheable(true);//开启二级查询缓存
        List<Student> studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        transaction.commit();
        session.close();

    }

    /**
     * 打开二级缓存更新数据
     * 当二级缓存中的数据被更新的时候，再次查询就会重新查询数据库
     */
    @Test
    public void openTowCacheUpdateDate(){
        transaction=session.getTransaction();
        transaction.begin();

        String hql="from Student s inner join s.teachar t where t.name=:name";
        Query query=session.createQuery(hql).setParameter("name","浙江教师");
        query.setCacheable(true);//开启二级查询缓存
        List<Student> studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        Student student=session.get(Student.class,2);
        student.setName("张三哈哈哈哈");


        studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));

        transaction.commit();
        session.close();
    }

    /**
     * 当二级缓存开启时候，一级缓存的地方就改成了二级缓存
     */
    @Test
    public void TowCacheTestOneCache(){
        transaction=session.getTransaction();
        transaction.begin();

        String hql="from Student s inner join s.teachar t where t.name=:name";
        Query query=session.createQuery(hql).setParameter("name","浙江教师");
        query.setCacheable(true);//开启二级查询缓存
        List<Student> studentList = query.list();
        System.out.println(Arrays.toString(studentList.toArray()));
        transaction.commit();
        session.close();
        session=sessionFactory.openSession();
        Student student=session.get(Student.class,2);
        System.out.println(student.toString());
    }

    @After
    public void after(){

        sessionFactory.close();
    }
}
