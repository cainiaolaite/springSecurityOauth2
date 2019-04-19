package com.hua.primary.service.impl;

import com.hua.primary.entity.User;
import com.hua.primary.vo.UserPageVo;
import org.hibernate.query.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 用户分页查询
 */
public class UserPageSpecification implements Specification<User> {
    /**
     * 用户分页查询对象
     */
    private UserPageVo userPageVo;
    public UserPageSpecification(UserPageVo userPageVo) {
        this.userPageVo = userPageVo;
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        //生成查询条件
        Predicate likePredicate=criteriaBuilder.like(root.<String>get("userName"),"%" + userPageVo.getUserName() + "%");//模糊条件
        //增加查询条件
        criteriaQuery.where(likePredicate);
        return criteriaQuery.getGroupRestriction();
    }
}
