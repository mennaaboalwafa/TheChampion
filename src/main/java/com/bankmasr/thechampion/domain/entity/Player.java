package com.bankmasr.thechampion.domain.entity;

import com.bankmasr.thechampion.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player extends AbstractAuditingEntity {
    private String playerName;
    private Long playerId;
    private String playerJobTitle;
    private Long playerStaffId;
    private String playerEmail;
    private boolean isChamp;
    private Long leagueId;
    private Long teamId;
    private Team teamByTeamId;


    @Basic
    @Column(name = "PLAYER_NAME")
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Id
    @GeneratedValue(generator = "PLAYER_SEQ")
    @Column(name = "PLAYER_ID")
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "PLAYER_JOB_TITLE")
    public String getPlayerJobTitle() {
        return playerJobTitle;
    }

    public void setPlayerJobTitle(String playerJobTitle) {
        this.playerJobTitle = playerJobTitle;
    }

    @Basic
    @Column(name = "PLAYER_STAFF_ID")
    public Long getPlayerStaffId() {
        return playerStaffId;
    }

    public void setPlayerStaffId(Long playerStaffId) {
        this.playerStaffId = playerStaffId;
    }

    @Basic
    @Column(name = "PLAYER_EMAIL")
    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) && Objects.equals(playerId, player.playerId) && Objects.equals(playerJobTitle, player.playerJobTitle) && Objects.equals(playerStaffId, player.playerStaffId)  && Objects.equals(playerEmail, player.playerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, playerId, playerJobTitle, playerStaffId,  playerEmail);
    }

    @Basic
    @Column(name = "IS_CHAMP")
    public boolean getIsChamp() {
        return isChamp;
    }

    public void setIsChamp(boolean champ) {
        isChamp = champ;
    }

    @Basic
    @Column(name = "LEAGUE_ID")
    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "TEAM_ID")
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @ManyToOne
    @JoinColumn(name="TEAM_ID",insertable = false,updatable = false )
    public Team getTeamByTeamId() {
        return teamByTeamId;
    }

    public void setTeamByTeamId(Team teamByTeamId) {
        this.teamByTeamId = teamByTeamId;
    }
}
