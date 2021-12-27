package com.bankmasr.thechampion.domain.entity;

import com.bankmasr.thechampion.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Team extends AbstractAuditingEntity {
    private String teamName;
    private Long teamId;
    private Long teamScore;
    private Boolean isWinner;
    private Match matchByMatchId;
    private Collection<Player> playersByTeamId;

    @Basic
    @Column(name = "TEAM_NAME")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Id
    @GeneratedValue(generator = "TEAM_SEQ")
    @Column(name = "TEAM_ID")
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "TEAM_SCORE")
    public Long getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Long teamScore) {
        this.teamScore = teamScore;
    }

    @Basic
    @Column(name = "IS_WINNER")
    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(Boolean winner) {
        isWinner = winner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName) && Objects.equals(teamId, team.teamId) && Objects.equals(teamScore, team.teamScore) && Objects.equals(isWinner, team.isWinner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamId, teamScore, isWinner);
    }

    @ManyToOne
    @JoinColumn(name = "MATCH_ID", referencedColumnName = "MATCH_ID")
    public Match getMatchByMatchId() {
        return matchByMatchId;
    }

    public void setMatchByMatchId(Match matchByMatchId) {
        this.matchByMatchId = matchByMatchId;
    }


    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL)
    public Collection<Player> getPlayersByTeamId() {
        return playersByTeamId;
    }

    public void setPlayersByTeamId(Collection<Player> playersByTeamId) {
        this.playersByTeamId = playersByTeamId;
    }
}