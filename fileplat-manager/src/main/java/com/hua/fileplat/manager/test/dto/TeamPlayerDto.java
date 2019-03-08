package com.hua.fileplat.manager.test.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 团队球员
 */
@Entity
@Table(name="PERSISTENCE_ROSTER_TEAM_PLAYER")
public class TeamPlayerDto implements Serializable {
    private String playerId;//球员ID
    private String teamId;//团队ID

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
