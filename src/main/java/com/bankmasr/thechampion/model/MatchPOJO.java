package com.bankmasr.thechampion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class MatchPOJO {

    private Long matchId;
    private Long matchScore;
    private Timestamp matchRealTime;
    private List<Long> teamsId;
    private Long roundId;
    private Long matchWinner;
    private String errorMessage;

    public MatchPOJO(long matchId, Timestamp matchRealTime, List<Long> teamsId) {
        this.matchId=matchId;
        this.matchRealTime=matchRealTime;
        this.teamsId=teamsId;
    }
}
