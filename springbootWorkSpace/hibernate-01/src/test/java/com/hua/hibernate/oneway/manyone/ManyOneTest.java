package com.hua.hibernate.oneway.manyone;

import com.hua.hibernate.oneway.manyone.Student;
import com.hua.hibernate.oneway.manyone.Teachar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManyOneTest {
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
     * 先保存学生  （不建议使用）
     * sql 日志
     * 1.创建student表
     * 2.创建teachar表
     * 3.为student表建立外键
     * 4.插入 student
     * 5.插入 student1
     * 6.插入teachar
     * 7.更新 student
     * 8.更新 student1
     *
     * 先插入的学生，但是此时的学生没有 teachar id
     * 后插入学生 更新 学生的teachar id
     */
    @Test
    public void saveStudent(){
        Teachar teachar=new Teachar();
        teachar.setName("中国黄冈人民教师");

        Student student=new Student();
        student.setName("黄冈学生");
        student.setTeachar(teachar);

        Student student1=new Student();
        student1.setName("溪水学生");
        student1.setTeachar(teachar);

        session.save(student);
        session.save(student1);
        session.save(teachar);

    }


    /**
     * 先保存教师
     * 1.插入 teachar
     * 2.插入 student
     * 3.插入 student1
     */
    @Test
    public void saveTeachar(){
        Teachar teachar=new Teachar();
        teachar.setName("中国湖北人民教师");

        Student student=new Student();
        student.setName("黄冈学生");
        student.setTeachar(teachar);

        Student student1=new Student();
        student1.setName("溪水学生");
        student1.setTeachar(teachar);

        session.save(teachar);
        session.save(student);
        session.save(student1);
    }

    /**
     * 延迟加载
     * 学生用时才加载
     * 老师不用就不加载
     */
    @Test
    public void loadStudent(){
        Student student=session.load(Student.class,5);
        System.out.println(student.getName());
    }

    /**
     * 延迟加载
     */
    @Test
    public void loadTeachar(){
        Student student=session.load(Student.class,5);
        System.out.println(student.getName());
        System.out.println(student.getTeachar().getName());
    }

    /**
     * 直接删除学生
     */
    @Test
    public void deleteStudent(){
        Student student=session.load(Student.class,5);
        session.delete(student);
    }

    /**
     * 直接删除老师
     * 注：不能直接删除，因为老师被外键约束(还有学生使用)
     */
    @Test
    public void deleteTeachar(){
        Teachar teachar=session.load(Teachar.class,3);
        session.delete(teachar);
    }


    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
