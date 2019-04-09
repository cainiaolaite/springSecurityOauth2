package com.hua.hibernate.towway.oneone.key;

import com.hua.hibernate.oneone.Computer;
import com.hua.hibernate.oneone.Cpu;
import com.hua.hibernate.oneone.IdCard;
import com.hua.hibernate.oneone.Person;
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
 * 主键作为外键
 */
public class KetTest {
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
     * 保存公司
     */
    @Test
    public void saveComputer(){
        Computer computer=new Computer();
        computer.setName("张三");
        Cpu cpu=new Cpu();
        cpu.setName("421282192503151712");
        computer.setCpu(cpu);
        cpu.setComputer(computer);
        session.save(computer);
        session.save(cpu);
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
