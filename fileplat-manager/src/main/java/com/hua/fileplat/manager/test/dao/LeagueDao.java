package com.hua.fileplat.manager.test.dao;

import com.hua.fileplat.manager.test.dto.LeagueDto;

/**
 * 联盟dao
 */
public interface LeagueDao {
    /**
     * 插入一个联盟
     * @param leagueDto
     * @return
     */
    boolean insertLeague(LeagueDto leagueDto);
}
