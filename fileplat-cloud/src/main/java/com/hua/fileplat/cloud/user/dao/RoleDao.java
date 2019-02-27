package com.hua.fileplat.cloud.user.dao;



import com.hua.fileplat.cloud.user.dto.RoleDto;

import java.util.List;

public interface RoleDao {
    List<RoleDto> selectRole();

    boolean updateRoleDto(RoleDto roleDto);
}
