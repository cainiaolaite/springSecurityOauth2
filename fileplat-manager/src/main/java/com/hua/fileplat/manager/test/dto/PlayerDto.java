package com.hua.fileplat.manager.test.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 球员
 */
@Entity
@Table(name="PERSISTENCE_ROSTER_PLAYER")
public class PlayerDto implements Serializable {
    @Id
    private String id;//球员id
    private String name;//姓名
    private String position;//位置
    private String salary;
    /**
     * Player 是 team 的另一方，或者说是不拥有的一方。
     * 作为一对一和多对一关系，非拥有端由关系注解中@ManyToMany的mappedby元素标记。
     * 因为Player和Team之间的关系是双向的，所以选择哪个实体是关系的所有者是任意的。
     */
    @ManyToMany(mappedBy = "playerDtoList")
    private List<TeamDto> teamDtoList;

    public List<TeamDto> getTeamDtoList() {
        return teamDtoList;
    }

    public void setTeamDtoList(List<TeamDto> teamDtoList) {
        this.teamDtoList = teamDtoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
