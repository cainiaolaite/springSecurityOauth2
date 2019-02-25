package com.hua.fileplat.cloud.dao;


import com.hua.fileplat.cloud.dto.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
public interface RoleDao {
    List<RoleDto> selectRole();

    boolean updateRoleDto(RoleDto roleDto);
}
