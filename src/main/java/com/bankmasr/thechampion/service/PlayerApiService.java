package com.bankmasr.thechampion.service;

import com.bankmasr.thechampion.model.PlayerPOJO;
import com.bankmasr.thechampion.model.TeamPOJO;

import java.util.List;


public interface PlayerApiService {

    PlayerPOJO save(PlayerPOJO playerPOJO);

    List<PlayerPOJO> getList();

    List<TeamPOJO> getParticipantTeams();

}
