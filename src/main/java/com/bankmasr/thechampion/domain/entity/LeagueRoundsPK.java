package com.bankmasr.thechampion.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class LeagueRoundsPK implements Serializable {
    private Long leagueId;
    private Long roundId;

    @Column(name = "LEAGUE_ID")
    @Id
    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @Column(name = "ROUND_ID")
    @Id
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueRoundsPK that = (LeagueRoundsPK) o;
        return Objects.equals(leagueId, that.leagueId) && Objects.equals(roundId, that.roundId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, roundId);
    }
}
