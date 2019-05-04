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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

public class HibernateQBCTest {
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
     * hibernate QBC 通过代码生成SQL
     *
         select
             student0_.ID as ID1_6_,
             student0_.NAME as NAME2_6_,
             student0_.SEX as SEX3_6_,
             student0_.TEACHER_ID as TEACHER_4_6_
         from
            hql_student student0_
         where
             (student0_.NAME like ?)
             and student0_.SEX=?
     */
    @Test
    public void qbcTest(){
        //SQL 语句建造者
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //创建查询语句 CriteriaQuery<Object>
        CriteriaQuery<Student> studentCriteriaQuery=criteriaBuilder.createQuery(Student.class);
        //源自于那个表
        Root<Student> sudentRoot = studentCriteriaQuery.from(Student.class);
        //生成查询条件
        Predicate likePredicate=criteriaBuilder.like(sudentRoot.<String>get("name"),"蕲春学生");//模糊条件
        Predicate equalPredicate=criteriaBuilder.equal(sudentRoot.<Boolean>get("sex"),"女");//等于条件
        Predicate wherePredicate=criteriaBuilder.and(likePredicate,equalPredicate);
        //增加查询条件
        studentCriteriaQuery.where(wherePredicate);
        //根据 面向对象的 sql 生成
        Query<Student> studentQuery = (Query<Student>)session.createQuery(studentCriteriaQuery);
        List<Student> list=studentQuery.list();
        System.out.println(list.toString());
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
