package com.bankmasr.thechampion.controller;

import com.bankmasr.thechampion.api.MatchApi;
import com.bankmasr.thechampion.model.MatchPOJOResponse;
import com.bankmasr.thechampion.model.MatchWinnerRequest;
import com.bankmasr.thechampion.service.MatchApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchApiController implements MatchApi {
    private final MatchApiService matchApiService;

    public MatchApiController(MatchApiService matchApiService) {
        this.matchApiService = matchApiService;
    }

    @Override
    public ResponseEntity<List<MatchPOJOResponse>> getListOfMatches() {
        if(matchApiService.getListOfMatches().stream().anyMatch(matchPOJO -> matchPOJO.getErrorMessage()!=null)){
            return new ResponseEntity<>(matchApiService.getListOfMatches(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchApiService.getListOfMatches(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateMatchWinner(MatchWinnerRequest matchWinnerRequest) {
        String response=matchApiService.updateMatchWinner(matchWinnerRequest.getMatchId(), matchWinnerRequest.getRoundId(), matchWinnerRequest.getWinnerTeamId());
        if (response==null)
            return new ResponseEntity<>("Match Updated", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }



    @Override
    public ResponseEntity<Boolean> closeTheRound(long roundId) {
        return new ResponseEntity<>(matchApiService.closeTheRound(roundId), HttpStatus.ACCEPTED);
    }
}
