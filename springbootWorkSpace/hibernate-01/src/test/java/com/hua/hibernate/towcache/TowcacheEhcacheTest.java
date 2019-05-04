package com.hua.hibernate.towcache;

import com.hua.hibernate.towway.manymany.Permission;
import com.hua.hibernate.towway.manymany.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * 二级缓存
 */
public class TowcacheEhcacheTest {
    //SessionFactoryImpl(MetadataImplementor metadata, SessionFactoryOptions options)
    private SessionFactory sessionFactory=null;
    private static final String HIBERANTE_CONFIG_FILE="hibernate-configuration.xml";
    private Session session;
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
     * 集合缓存
     * Role permissionSet 集合有 做过集合缓存
     * 执行结果 是 permissionSet 集合被缓存了 没有打印SQL语句
     */
    @Test
    public void collectionCache(){
        Role role=session.get(Role.class,1);
        System.out.println(Arrays.toString(role.getPermissionSet().toArray()));
        session.close();
        session=sessionFactory.openSession();
        role=session.get(Role.class,1);
        System.out.println(Arrays.toString(role.getPermissionSet().toArray()));
        session.close();
    }




    @After
    public void after(){
        sessionFactory.close();
    }
}
