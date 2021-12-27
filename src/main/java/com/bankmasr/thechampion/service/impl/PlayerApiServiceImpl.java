package com.bankmasr.thechampion.service.impl;

import com.bankmasr.thechampion.domain.entity.Player;
import com.bankmasr.thechampion.domain.entity.Team;
import com.bankmasr.thechampion.model.PlayerPOJO;
import com.bankmasr.thechampion.model.TeamPOJO;
import com.bankmasr.thechampion.presistence.dao.PlayerApiDao;
import com.bankmasr.thechampion.presistence.dao.TeamDao;
import com.bankmasr.thechampion.service.PlayerApiService;
import com.bankmasr.thechampion.service.mapper.ServiceObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PlayerApiServiceImpl implements PlayerApiService {
    private final PlayerApiDao playerApiDao;
    private final TeamDao teamDao;
    private final ServiceObjectMapper serviceObjectMapper;


    public PlayerApiServiceImpl(PlayerApiDao playerApiDao, TeamDao teamDao, ServiceObjectMapper serviceObjectMapper) {
        this.playerApiDao = playerApiDao;
        this.teamDao = teamDao;
        this.serviceObjectMapper = serviceObjectMapper;
    }


    /**
     * @return PlayerPOJO
     * submit request of player
     */
    @Override
    public PlayerPOJO save(PlayerPOJO playerPOJO) {
        Player player = serviceObjectMapper.map(playerPOJO, Player.class);
        //    validate request
        String errorMessage = validate(playerPOJO);
        if (errorMessage != null) {
            playerPOJO.setErrorMessage(errorMessage);
            return playerPOJO;
        }
        return serviceObjectMapper.map(playerApiDao.save(player), PlayerPOJO.class);
    }


    private String validate(PlayerPOJO playerPOJO) {
        // check if player exist before
        Player player = playerApiDao.getByStaffId(playerPOJO.getPlayerStaffId());
        if (player != null) {
            return ("you already registered");
        }
        return null;
    }

    @Override
    public List<PlayerPOJO> getList() {
        List<Player> players = playerApiDao.getAll();
        return serviceObjectMapper.mapList(players, PlayerPOJO.class);
    }

    @Override
    public List<TeamPOJO> getParticipantTeams() {
        List<Team> teams = createTeam(playerApiDao.count());
        teams.forEach(team -> {
            playerApiDao.findRandomPlayers().forEach(player -> {
                player.setTeamId(team.getTeamId());
                playerApiDao.save(player);
            });
        });
        return serviceObjectMapper.mapTeamToTeamPOJO(teamDao.getAll());
    }

    private List<Team> createTeam(long playerCount) {
        List<Team> teams = new ArrayList<>();
        playerCount = Math.round(playerCount / 2.0);

        // create team
        for (int i = 0; i < playerCount; i++) {
            Team team = teamDao.save(createTeam());
            teams.add(team);
        }

        return teams;
    }


    private Team createTeam() {
        Team team = new Team();
        team.setTeamName(generateAnonymousTeamName());
        team.setTeamScore(0L);
        team.setWinner(false);
        return team;
    }

    private String generateAnonymousTeamName() {
        // Generated name for anonymous team creation scenario
        return "ANONYMOUS-" + UUID.randomUUID();
    }

}
