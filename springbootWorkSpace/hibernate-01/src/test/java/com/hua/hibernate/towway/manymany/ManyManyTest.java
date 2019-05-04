package com.hua.hibernate.towway.manymany;

import com.hua.hibernate.towway.manyone.Company;
import com.hua.hibernate.towway.manyone.Department;
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
 * 双向多对多
 */
public class ManyManyTest {

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
        Permission permission=new Permission();
        permission.setName("呼吸");

        Permission permission1=new Permission();
        permission1.setName("睡觉");

        Role role=new Role();
        role.setName("青蛙");

        Role test=new Role();
        test.setName("钢铁侠");

        role.getPermissionSet().add(permission);
        role.getPermissionSet().add(permission1);

        test.getPermissionSet().add(permission);
        test.getPermissionSet().add(permission1);

        permission.getRoleSet().add(role);
        permission.getRoleSet().add(test);

        permission1.getRoleSet().add(role);
        permission1.getRoleSet().add(test);

        session.save(role);
        session.save(test);
        session.save(permission);
        session.save(permission1);
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
