package com.bankmasr.thechampion.api;


import com.bankmasr.thechampion.model.MatchPOJO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/league", produces = "application/json")
public interface LeagueApi {

    @PostMapping("/newMatch")
    ResponseEntity<String> submitNewMatch(@RequestBody @Valid MatchPOJO matchPOJO);

    @PostMapping("/champ/{playerId}")
    ResponseEntity<String> submitLeagueChampion(@PathVariable @Valid Long playerId);

}
