package com.hua.hibernate.extend.subclass;

import com.hua.hibernate.extend.subclass.Animal;
import com.hua.hibernate.extend.subclass.Big;
import com.hua.hibernate.extend.subclass.Bird;
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
 * 关联策略
 */
public class SubclassTest {
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
    public void saveAnimal(){
        Animal animal=new Animal();
        animal.setName("张三");
        animal.setSex("男");

        Big big=new Big();
        big.setName("天蓬");
        big.setSex("公");
        big.setWeight(200);

        Bird bird=new Bird();
        bird.setName("凤凰");
        bird.setSex("母");
        bird.setHeight(200);

        session.save(animal);
        session.save(big);
        session.save(bird);
    }


    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
