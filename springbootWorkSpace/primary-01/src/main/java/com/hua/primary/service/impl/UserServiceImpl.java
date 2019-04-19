package com.hua.primary.service.impl;

import com.hua.primary.dao.CompanyDao;
import com.hua.primary.dao.UserDao;
import com.hua.primary.entity.Company;
import com.hua.primary.entity.User;
import com.hua.primary.service.UserService;
import com.hua.primary.vo.UserPageVo;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryHint;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

/**
 * 用户服务层
 */
@Service
public class UserServiceImpl implements UserService{
    /**
     * 用户DAO
     */
    @Autowired
    private UserDao userDao;

    /**
     * 公司
     */
    @Autowired
    private CompanyDao companyDao;


    /**
     * 用户分页查询
     * @param pageable
     * @return
     */
    @Override
    public Page<User> userPage(UserPageVo userPageVo,Pageable pageable){
        Specification<User> userSpecification=new UserPageSpecification(userPageVo);
        return userDao.findAll(userSpecification,pageable);
    }



    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User saveUser(User user) {
        user=userDao.save(user);
        return user;
    }

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    @Override
    public User getUser(User user) {
        User user1 = userDao.getOne("402880036a34656b016a34657add0001");
        return user1;
    }


}
