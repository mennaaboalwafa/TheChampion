package com.bankmasr.thechampion.api;

import com.bankmasr.thechampion.model.MatchPOJOResponse;
import com.bankmasr.thechampion.model.MatchWinnerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/match", produces = "application/json")
public interface MatchApi {

    @GetMapping
    ResponseEntity<List<MatchPOJOResponse>> getListOfMatches();

    @PutMapping("/updateMatch")
    ResponseEntity<String> updateMatchWinner(@RequestBody @Valid MatchWinnerRequest matchWinnerRequest);


    @PutMapping("/close/{roundId}")
    ResponseEntity<Boolean> closeTheRound(@PathVariable long roundId);
}
