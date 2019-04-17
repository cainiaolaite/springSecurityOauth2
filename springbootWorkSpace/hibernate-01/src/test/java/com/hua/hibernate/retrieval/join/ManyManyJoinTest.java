package com.hua.hibernate.retrieval.join;

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

import java.util.Iterator;

/**
 * 左外连接检索策略
 */
public class ManyManyJoinTest {
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
     * 保存角色
     */
    @Test
    public void saveRole(){
        Student student=new Student();
        student.setName("黄冈学生");
        Student student1=new Student();
        student.setName("溪水学生");
        Student student2=new Student();
        student.setName("蕲春学生");
        Teachar teachar=new Teachar();
        teachar.setName("湖北教师");
        student.setTeachar(teachar);
        student1.setTeachar(teachar);
        student2.setTeachar(teachar);
        session.save(student);
        session.save(student1);
        session.save(student2);
        session.save(teachar);
        System.out.println(teachar);
    }

    /**
     * 延迟查询
     * <set table="join_student" name="studentSet" inverse="true"  lazy="true"  batch-size="2">
     */
    @Test
    public void fetchSubSelectBatchSize(){
        Teachar teachar=session.get(Teachar.class,1);

    }

    /**
     * 延迟查询
     * <set table="join_student" name="studentSet" inverse="true"  lazy="true"  batch-size="2">
     * teachar 查询了 两片
     */
    @Test
    public void fetchSubSelectBatchSize1(){
        String hql="from Teachar";
        Query<Teachar> query=session.createQuery(hql);
        System.out.println(query.list().size());

        for(Teachar teachar:query.getResultList()){
            Iterator<Student> iterator=teachar.getStudentSet().iterator();
            System.out.println(iterator.next());
        }

    }
    
    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
