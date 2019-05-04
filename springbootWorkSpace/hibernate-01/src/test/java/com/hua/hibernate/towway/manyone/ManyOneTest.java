package com.hua.hibernate.towway.manyone;

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
 * 双向多对一
 */
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
     * 保存公司部门
     * inverse=true   双向多对一 由单方（由多的那一端（department））维护关系
     * 所以保存的时候 没有去更新 department
     */
    @Test
    public void saveCompanyDepartment(){
        Company company=new Company();
        company.setName("武汉沈义科技有限公司");
        Department department=new Department();
        department.setName("研发部");
        department.setCompany(company);
        Department department1=new Department();
        department1.setName("市场部");
        department1.setCompany(company);
        company.getDepartmentSet().add(department);
        company.getDepartmentSet().add(department1);
        session.save(company);
        session.save(department);
        session.save(department1);
    }


    /**
     * 级联删除 公司，公司旗下的部门也会删除
     * cascade="delete"
     * Hibernate:  delete from department  where ID=?
     * Hibernate:  delete from department  where ID=?
     * Hibernate:  delete from company     where ID=?
     */
    @Test
    public void cascadeDeleteCompany(){
        Company company=session.get(Company.class,1);
        System.out.println(company);
        session.delete(company);
    }



    /**
     * 级联保存
     * cascade="save-update"
     * 公司 部门 双向关系  只需要保存公司就可以了（部门会跟着保存）
     */
    @Test
    public void cascadeSaveUpdateCompany(){
        Company company=new Company();
        company.setName("武汉沈义科技有限公司");
        Department department=new Department();
        department.setName("研发部");
        department.setCompany(company);
        Department department1=new Department();
        department1.setName("市场部");
        department1.setCompany(company);
        company.getDepartmentSet().add(department);
        company.getDepartmentSet().add(department1);
        session.save(company);
    }

    /**
     * order-by 指的排序  以 department 表 id 做降序排列
     * order-by="ID DESC"
     select
        department0_.COMPANY_ID as COMPANY_3_1_0_,
        department0_.ID as ID1_1_0_,
        department0_.ID as ID1_1_1_,
        department0_.NAME as NAME2_1_1_,
        department0_.COMPANY_ID as COMPANY_3_1_1_
     from
        department department0_
     where
        department0_.COMPANY_ID=?
     order by
        department0_.ID desc
     */
    @Test
    public void departmentOrderByIdDesc(){
        Company company=session.get(Company.class,1);
        System.out.println(company);
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
