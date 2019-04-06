package com.hua.hibernate.onecache.method;

import com.hua.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Hibernate 的一级缓存-get测试
 * Hibernate 的一级缓存就是Session 的缓存
 */
public class HibernateMethodTest {
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
        /*transaction=session.getTransaction();
        transaction.begin();*/
    }

    /**
     * 持久化对象，重复查询测试
     * 测试是否执行了查询语句
     */
    @Test
    public void getUser(){
        User user=session.get(User.class,"000000000000000000000");
        System.out.println("第一次从数据库中取出id为【000000000000000000000】:"+user.toString());
        User user2=session.get(User.class,"000000000000000000000");
        System.out.println("第二次从数据库中取出id为【000000000000000000000】:"+user2.toString());
        System.out.println("如日志所示：session.get()两次重复取出数据库中的相同的数据，\n第一次是到数据库中提取，\n第二次是到session的缓存中提取:");
    }

    /**
     * 瞬时对象 保存 是否持久化
     * 持久化之后 更新 提交事务  是否执行了 update
     */
    @Test
    public void saveUser(){
        Transaction transaction=session.getTransaction();
        transaction.begin();
        User user=new User();
        user.setId("00"+System.currentTimeMillis());
        user.setUserName("hahha");
        user.setPassword("hahha");
        user.setEmail("1308189967@qq.com");
        user.setPhone("13476096121");
        user.setAddress("武汉");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //user对象被新建好后 状态为 临时状态，不被session托管
        session.save(user);
        System.out.println("对象被保存（已经持久化）:"+user.toString());
        user.setUserName("wuhaihuahaha");
        user.setAddress("江西");
        System.out.println("持久对象被更新了字段:"+user.toString());
        transaction.commit();
    }

    @Test
    public void loadUser(){
        Transaction transaction=session.getTransaction();
        transaction.begin();
        User user=session.load(User.class,"000000000000000000000");
        user.setUserName("wuhaihuahaha11");
        user.setAddress("江西22");
        transaction.commit();
    }


    /**
     * 持久化对象不能更新ID
     * identifier of an instance of com.hua.hibernate.entity.
     * User was altered from 000000000000000000000 to 1111111123123123identifier of an instance of
     * com.hua.hibernate.entity.User was altered from 000000000000000000000 to 1111111123123123
     */
    @Test
    public void loadUserUpdateId(){
        Transaction transaction=session.getTransaction();
        transaction.begin();
        User user=session.load(User.class,"000000000000000000000");
        user.setUserName("wuhaihuahaha11");
        user.setAddress("江西22");
        user.setId("1111111123123123");
        transaction.commit();
    }

    @Test
    public void updateUser(){
        /*Transaction transaction=session.getTransaction();
        transaction.begin();*/
        User user=session.load(User.class,"000000000000000000000");
        user.setUserName("wuhaihuahaha11");
        user.setAddress("江西22111");
        System.out.println("持久对象被更新了字段:"+user.toString());
        session.update(user);
    }



    @After
    public void after(){
        /*transaction.commit();*/
        session.close();
        sessionFactory.close();
    }

}