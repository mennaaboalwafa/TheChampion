package com.bankmasr.thechampion.service;

import com.bankmasr.thechampion.model.MatchPOJOResponse;

import java.util.List;

public interface MatchApiService {


    List<MatchPOJOResponse> getListOfMatches();

    boolean closeTheRound(long roundId);

    String updateMatchWinner(Long matchId, Long roundId, Long teamWinnerId);
}
