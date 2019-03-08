package com.hua.fileplat.manager.test.dao;

import com.hua.fileplat.manager.test.dto.TeamDto;

/**
 * 团队dao
 */
public interface TeamDao {
    /**
     * 插入一个团队
     * @param teamDto
     * @return
     */
    boolean insertTeam(TeamDto teamDto);
}
