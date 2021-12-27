package com.bankmasr.thechampion.model;

import lombok.Data;

@Data
public class MatchWinnerRequest {
    Long matchId; Long roundId; Long winnerTeamId;

}
