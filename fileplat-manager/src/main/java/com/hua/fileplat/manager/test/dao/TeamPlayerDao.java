package com.hua.fileplat.manager.test.dao;

import com.hua.fileplat.manager.test.dto.TeamDto;
import com.hua.fileplat.manager.test.dto.TeamPlayerDto;

/**
 * 团队成员dao
 */
public interface TeamPlayerDao {
    /**
     * 插入一个团队成员
     * @param teamPlayerDto
     * @return
     */
    boolean insertTeamPlayer(TeamPlayerDto teamPlayerDto);
}
