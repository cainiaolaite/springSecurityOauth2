package com.hua.fileplat.cloud;

import com.hua.fileplat.cloud.aop.service.UserService;
import com.hua.fileplat.cloud.service.RoleService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWordTest {
    @Test
    public void inputHelloWord(){
        //Assert.assertEquals("HelloWord",new HelloWord().input());
    }

    @Test
    public void springAopTest(){
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        UserService userService=(UserService)applicationContext.getBean("userServiceImpl");
        userService.add();
        //DataSourceTransactionManager dataSourceTransactionManager=(DataSourceTransactionManager)applicationContext.getBean("transactionManager");
        RoleService roleService=(RoleService)applicationContext.getBean("roleServiceImpl");
        //List<RoleDto> roleList= roleService.selectRole();
        roleService.transactionTest();
        roleService.selectRole();
    }
}
