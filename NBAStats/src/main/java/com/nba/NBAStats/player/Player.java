package com.nba.NBAStats.player;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="player_stats")
public class Player {
    @Id
    @Column(name = "id", unique = true)
    private Integer id;
    private String name;
    private String team;
    private Integer age;
    private Double height;
    private Integer gp;
    private Double points;
    private Double rebounds;
    private Double assists;

    public Player() {
    }

    public Player(Integer id, String name, String team, Integer age, Double height, Integer gp, Double rebounds, Double points, Double assists) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.age = age;
        this.height = height;
        this.gp = gp;
        this.rebounds = rebounds;
        this.points = points;
        this.assists = assists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getGp() {
        return gp;
    }

    public void setGp(Integer gp) {
        this.gp = gp;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getRebounds() {
        return rebounds;
    }

    public void setRebounds(Double rebounds) {
        this.rebounds = rebounds;
    }

    public Double getAssists() {
        return assists;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }
}
