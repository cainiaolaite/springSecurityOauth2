package com.hua.fileplat.manager.test.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 联盟
 */
@Entity
@Table(name="PERSISTENCE_ROSTER_LEAGUE")
public class LeagueDto implements Serializable{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) 自动创建ID
    private String id;
    private String dtype;
    private String name;
    private String sport;
    /**
     * 一个联盟 有多少球队
     */
    @OneToMany
    @JoinTable(
            name = "PERSISTENCE_ROSTER_TEAM",
            joinColumns = @JoinColumn(name = "LEAGUE_ID",referencedColumnName = "ID")//关联球队
    )
    private List<TeamDto> teamDtoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
