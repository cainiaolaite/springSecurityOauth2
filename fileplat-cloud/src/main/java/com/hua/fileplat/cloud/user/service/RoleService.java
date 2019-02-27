package com.hua.fileplat.cloud.user.service;


import com.hua.fileplat.cloud.user.dto.RoleDto;

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
