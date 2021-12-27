package com.bankmasr.thechampion.domain.entity;

import com.bankmasr.thechampion.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Round extends AbstractAuditingEntity {
    private Long roundId;
    private Boolean roundClose;


    @Id
    @GeneratedValue(generator = "ROUND_SEQ")
    @Column(name = "ROUND_ID")
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    @Basic
    @Column(name = "ROUND_CLOSE")
    public Boolean getRoundClose() {
        return roundClose;
    }

    public void setRoundClose(Boolean roundClose) {
        this.roundClose = roundClose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return Objects.equals(roundId, round.roundId) && Objects.equals(roundClose, round.roundClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, roundClose);
    }


}
