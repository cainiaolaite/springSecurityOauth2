package com.hua.fileplat.manager.test.dto;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 团队
 */
@Entity
@Table(name="PERSISTENCE_ROSTER_TEAM")
public class TeamDto implements Serializable {
    @Id
    private String id;
    private String city;
    private String name;
    private String leagueId;//联盟id
    /**
     * 一个球队 多个球员，一个球员可以参加多个球队
     * @JoinTable 注解用于指定数据库，将 运动员 ID 与 端对的ID 管理。
     * 指定的是提示@JoinTable关系的所有者，因此Team实体与Player实体的关系的所有者。
     */
    @ManyToMany
    @JoinTable(
            name = "PERSISTENCE_ROSTER_TEAM_PLAYER",//管理表名
            joinColumns = @JoinColumn(name="TEAM_ID",referencedColumnName = "ID"),//连接列
            inverseJoinColumns = @JoinColumn(name="PLAYER_ID",referencedColumnName = "ID")//反向连接列
    )
    private List<PlayerDto> playerDtoList;

    /**
     * 一个球队一个联盟
     */
    @OneToOne
    @JoinTable(
            name = "PERSISTENCE_ROSTER_LEAGUE",
            joinColumns = @JoinColumn(name="ID",referencedColumnName = "LEAGUE_ID")
    )
    private LeagueDto leagueDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public List<PlayerDto> getPlayerDtoList() {
        return playerDtoList;
    }

    public void setPlayerDtoList(List<PlayerDto> playerDtoList) {
        this.playerDtoList = playerDtoList;
    }
}
