package com.hua.primary.dao;

import com.hua.primary.entity.Company;
import com.hua.primary.entity.User;
import com.hua.primary.service.UserService;
import com.hua.primary.vo.UserPageVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 用户Dao 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    /**
     * 用户持久层
     */
    @Autowired
    public UserDao userDao;

    /**
     * 公司持久层
     */
    @Autowired
    public CompanyDao companyDao;

    @Autowired
    public UserService userService;

    @Test
    public void saveTest(){
        User user=new User();
        user.setUserName("wuhaihua");
        user.setPassword("123456");
        user.setPhone("13476096122");
        user.setEmail("1308189961@qq.com");
        user.setSalt("123");
        user.setType(1);
        user.setStatus(1);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        user.setCreateTime(timestamp);

        Company company=new Company();
        company.setName("武汉沈义科技有极限公司");
        company.setType(1);
        company.setCreateTime(timestamp);
        company.setStatus(1);
        company.setDescription("数字证书签名加密");
        user.setCompany(company);
        companyDao.save(company);
        userService.saveUser(user);
    }

    @Test
    public void pageTest(){
        UserPageVo userPageVo=new UserPageVo();
        userPageVo.setUserName("cai");
        Page<User> users=userService.userPage(userPageVo,new PageRequest(1,2));
        System.out.println(users.getContent().size());
    }


}
