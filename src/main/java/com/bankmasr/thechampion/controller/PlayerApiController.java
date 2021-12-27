package com.bankmasr.thechampion.controller;

import com.bankmasr.thechampion.api.PlayerApi;
import com.bankmasr.thechampion.model.PlayerPOJO;
import com.bankmasr.thechampion.model.TeamPOJO;
import com.bankmasr.thechampion.service.PlayerApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerApiController implements PlayerApi {
    private final PlayerApiService playerApiService;

    public PlayerApiController(PlayerApiService playerApiService) {
        this.playerApiService = playerApiService;
    }

    @Override
    public ResponseEntity<PlayerPOJO> submitRequest(PlayerPOJO playerPOJO) {
        return new ResponseEntity<>(playerApiService.save(playerPOJO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PlayerPOJO>> getListOfPlayers() {
        return new ResponseEntity<>(playerApiService.getList(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeamPOJO>> getParticipantTeams() {
        if (playerApiService.getParticipantTeams()!=null)
        return new ResponseEntity<>(playerApiService.getParticipantTeams(), HttpStatus.CREATED);
        else return new ResponseEntity<>(playerApiService.getParticipantTeams(), HttpStatus.NOT_MODIFIED);
    }
}
