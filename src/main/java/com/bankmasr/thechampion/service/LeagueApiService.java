package com.bankmasr.thechampion.service;

import com.bankmasr.thechampion.model.MatchPOJO;

public interface LeagueApiService {
    String submitNewMatch(MatchPOJO leagueRequestPOJO);

    String submitLeagueChampion(Long playerId);
}
