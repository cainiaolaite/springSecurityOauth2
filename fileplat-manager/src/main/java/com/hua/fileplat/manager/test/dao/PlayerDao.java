package com.hua.fileplat.manager.test.dao;

import com.hua.fileplat.manager.test.dto.PlayerDto;

/**
 * 远动员dao
 */
public interface PlayerDao {
    /**
     * 插入一个远动员
     * @param playerDto
     * @return
     */
    boolean insertPlayer(PlayerDto playerDto);
}
