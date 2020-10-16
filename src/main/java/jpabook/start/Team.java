package jpabook.start;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
