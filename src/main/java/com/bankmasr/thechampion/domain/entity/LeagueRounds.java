package com.bankmasr.thechampion.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LEAGUE_ROUNDS", schema = "TENNIS_TABLE_LEAGUE")
@IdClass(LeagueRoundsPK.class)
public class LeagueRounds {
    private Long leagueId;
    private Long roundId;
    private League leagueByLeagueId;

    @Id
    @Column(name = "LEAGUE_ID")
    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @Id
    @Column(name = "ROUND_ID")
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
        LeagueRounds that = (LeagueRounds) o;
        return Objects.equals(leagueId, that.leagueId) && Objects.equals(roundId, that.roundId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, roundId);
    }

    @ManyToOne
    @MapsId("leagueId")
    @JoinColumn(name = "LEAGUE_ID", referencedColumnName = "LEAGUE_ID", nullable = false)
    public League getLeagueByLeagueId() {
        return leagueByLeagueId;
    }

    public void setLeagueByLeagueId(League leagueByLeagueId) {
        this.leagueByLeagueId = leagueByLeagueId;
    }


}
