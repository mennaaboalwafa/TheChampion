package com.bankmasr.thechampion.model;

import com.bankmasr.thechampion.domain.entity.Match;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MatchPOJOResponse {

    private Long matchId;
    private Long matchScore;
    private Timestamp matchRealTime;
    private List<TeamPOJO> teamPOJO;
    private Long roundId;
    private TeamPOJO matchWinner;
    private String errorMessage;


    public MatchPOJOResponse(List<TeamPOJO> teamPOJOS, TeamPOJO team) {
        this.teamPOJO=teamPOJOS;
        this.matchWinner=team;
    }

    public MatchPOJOResponse(List<TeamPOJO> teamPOJOS, TeamPOJO team, Match match) {
        this.matchId=match.getMatchId();
        this.matchScore=match.getMatchScore();
        this.matchRealTime=match.getMatchRealTime();
        this.teamPOJO=teamPOJOS;
        this.matchWinner=team;
    }
}
