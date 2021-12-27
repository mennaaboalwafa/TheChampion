package com.bankmasr.thechampion.domain.entity;

import com.bankmasr.thechampion.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
public class League extends AbstractAuditingEntity {

    private Long leagueId;
    private Time startDate;
    private Time endDate;
    private Boolean hasChampion;
    private String leagueDesc;
    private Round roundByCurrentRound;
    private Boolean isClosed;


    @Id
    @GeneratedValue(generator = "LEAGUE_SEQ")
    @Column(name = "LEAGUE_ID")
    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "START_DATE")
    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "HAS_CHAMPION")
    public Boolean getHasChampion() {
        return hasChampion;
    }

    public void setHasChampion(Boolean hasChampion) {
        this.hasChampion = hasChampion;
    }

    @Basic
    @Column(name = "LEAGUE_DESC")
    public String getLeagueDesc() {
        return leagueDesc;
    }

    public void setLeagueDesc(String leagueDesc) {
        this.leagueDesc = leagueDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        League league = (League) o;
        return Objects.equals(leagueId, league.leagueId) && Objects.equals(startDate, league.startDate) && Objects.equals(endDate, league.endDate) && Objects.equals(hasChampion, league.hasChampion) && Objects.equals(leagueDesc, league.leagueDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, startDate, endDate, hasChampion, leagueDesc);
    }

    @ManyToOne
    @JoinColumn(name = "CURRENT_ROUND", referencedColumnName = "ROUND_ID")
    public Round getRoundByCurrentRound() {
        return roundByCurrentRound;
    }

    public void setRoundByCurrentRound(Round roundByCurrentRound) {
        this.roundByCurrentRound = roundByCurrentRound;
    }

    @Basic
    @Column(name = "IS_CLOSED")
    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean closed) {
        isClosed = closed;
    }


}
