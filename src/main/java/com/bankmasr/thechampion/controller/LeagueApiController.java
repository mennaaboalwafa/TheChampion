package com.bankmasr.thechampion.controller;

import com.bankmasr.thechampion.api.LeagueApi;
import com.bankmasr.thechampion.model.MatchPOJO;
import com.bankmasr.thechampion.service.LeagueApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeagueApiController implements LeagueApi {
    private final LeagueApiService leagueApiService;

    public LeagueApiController(LeagueApiService leagueApiService) {
        this.leagueApiService = leagueApiService;
    }


    @Override
    public ResponseEntity<String> submitNewMatch(MatchPOJO matchPOJO) {
        String result = leagueApiService.submitNewMatch(matchPOJO);
        if (result == null)
            return new ResponseEntity<>("Match Submitted", HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> submitLeagueChampion(Long playerId) {
        String result=leagueApiService.submitLeagueChampion(playerId);
        if (result == null)
            return new ResponseEntity<>("Champion Submitted", HttpStatus.ACCEPTED);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
