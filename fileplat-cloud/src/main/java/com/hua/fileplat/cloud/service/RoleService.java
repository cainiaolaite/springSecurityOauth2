package com.hua.fileplat.cloud.service;

import com.hua.fileplat.cloud.dto.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService {
    List<RoleDto> selectRole();

    /**
     * 事务测试
     * @return
     */
    boolean transactionTest();
}
