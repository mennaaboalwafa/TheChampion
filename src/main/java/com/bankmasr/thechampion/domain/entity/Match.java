package com.bankmasr.thechampion.domain.entity;

import com.bankmasr.thechampion.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Match extends AbstractAuditingEntity {
    private Long matchId;
    private Long matchScore=0L;
    private Timestamp matchRealTime;
    private List<Team> teams;
    private Long roundId;
    private Long matchWinner;

    @Id
    @GeneratedValue(generator = "MATCH_SEQ")
    @Column(name = "MATCH_ID")
    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "MATCH_SCORE")
    public Long getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Long matchScore) {
        this.matchScore = matchScore;
    }

    @Basic
    @Column(name = "MATCH_REAL_TIME")
    public Timestamp getMatchRealTime() {
        return matchRealTime;
    }

    public void setMatchRealTime(Timestamp matchRealTime) {
        this.matchRealTime = matchRealTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchId, match.matchId) && Objects.equals(matchScore, match.matchScore) && Objects.equals(matchRealTime, match.matchRealTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId,  matchScore, matchRealTime);
    }

    @OneToMany(mappedBy = "matchByMatchId", fetch = FetchType.LAZY)
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Basic
    @Column(name = "ROUND_ID")
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    @Basic
    @Column(name = "MATCH_WINNER")
    public Long getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(Long matchWinner) {
        this.matchWinner = matchWinner;
    }
}
