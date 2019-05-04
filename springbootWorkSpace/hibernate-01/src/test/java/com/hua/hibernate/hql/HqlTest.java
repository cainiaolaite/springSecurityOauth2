package com.hua.hibernate.hql;


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
import java.util.Iterator;
import java.util.List;

public class HqlTest {
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


    /**
     * 保存学生
     */
    @Test
    public void saveStudent(){
        Student student=new Student();
        student.setName("黄冈学生");
        student.setSex("男");
        Student student1=new Student();
        student1.setName("溪水学生");
        Student student2=new Student();
        student2.setName("蕲春学生");
        Student student3=new Student();
        student3.setName("绍兴学生");
        Student student4=new Student();
        student4.setName("黄石学生");
        student4.setSex("男");
        Student student5=new Student();
        student5.setName("羊群胡学生");
        Student student6=new Student();
        student6.setSex("男");
        student6.setName("济南学生");
        Teachar teachar=new Teachar();
        teachar.setName("浙江教师");
        student.setTeachar(teachar);
        student1.setTeachar(teachar);
        student2.setTeachar(teachar);
        student3.setTeachar(teachar);
        student4.setTeachar(teachar);
        student5.setTeachar(teachar);
        student6.setTeachar(teachar);
        session.save(student);
        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
        session.save(student5);
        session.save(student6);
        session.save(teachar);
        System.out.println(teachar);
    }


    /**
     * 分页查询学生
     * hql 表 就是 对象
     */
    @Test
    public void pageQueryStudent(){
        String hql="from Student";
        int pageNumber=2;//第几页
        int pageSize=3;//一页 几条数据
        int startPosition=(pageNumber-1)*pageSize;//开始查询的位置
        int maxResult=pageSize;//最大的结果集
        Query query=session.createQuery(hql).setFirstResult(startPosition).setMaxResults(maxResult);
        List<Student> studentList = query.getResultList();
        System.out.println(Arrays.toString(studentList.toArray()));
    }

    /**
     * 分页查询 Hql 语句映射到文件中
     */
    @Test
    public void pageQueryHqlMapping(){
        int pageNumber=2;//第几页
        int pageSize=3;//一页 几条数据
        int startPosition=(pageNumber-1)*pageSize;//开始查询的位置
        int maxResult=pageSize;//最大的结果集
        Query query=session.getNamedQuery("studentPageQuery").
                setFirstResult(startPosition).
                setMaxResults(maxResult).
                setParameter("sex","女");
        List<Student> studentList = query.getResultList();
        System.out.println(Arrays.toString(studentList.toArray()));
    }

    /**
     * hql 只显示部分属性
     */
    @Test
    public void pageQueryHqlAttriberMapping(){
        int pageNumber=2;//第几页
        int pageSize=3;//一页 几条数据
        int startPosition=(pageNumber-1)*pageSize;//开始查询的位置
        int maxResult=pageSize;//最大的结果集
        Query query=session.getNamedQuery("studentPageQueryAttriber").
                setFirstResult(startPosition).
                setMaxResults(maxResult).
                setParameter("sex","女");
        List<Student> studentList = query.getResultList();
        System.out.println(Arrays.toString(studentList.toArray()));
    }


    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
