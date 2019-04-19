package com.hua.primary.dao;

import com.hua.primary.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 公司持久层
 */
public interface CompanyDao extends JpaRepository<Company,String>,JpaSpecificationExecutor<Company> {
}
