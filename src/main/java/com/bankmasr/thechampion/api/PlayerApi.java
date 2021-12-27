package com.bankmasr.thechampion.api;

import com.bankmasr.thechampion.model.PlayerPOJO;
import com.bankmasr.thechampion.model.TeamPOJO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/player", produces = "application/json")
public interface PlayerApi {

    @PostMapping
    ResponseEntity<PlayerPOJO> submitRequest(@RequestBody @Valid PlayerPOJO playerPOJO);

    @GetMapping
    ResponseEntity<List<PlayerPOJO>> getListOfPlayers();

    @GetMapping("/list/teams")
    ResponseEntity<List<TeamPOJO>> getParticipantTeams();
}
