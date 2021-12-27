package com.bankmasr.thechampion.service.impl;

import com.bankmasr.thechampion.domain.entity.*;
import com.bankmasr.thechampion.model.MatchPOJO;
import com.bankmasr.thechampion.presistence.dao.*;
import com.bankmasr.thechampion.service.LeagueApiService;
import com.bankmasr.thechampion.service.mapper.ServiceObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueApiServiceImpl implements LeagueApiService {

    private final MatchApiDao matchApiDao;

    private final PlayerApiDao playerApiDao;
    private final RoundDao roundDao;
    private final ServiceObjectMapper mapper;
    private final LeagueDao leagueDao;
    private final TeamDao teamDao;

    public LeagueApiServiceImpl(MatchApiDao matchApiDao, PlayerApiDao playerApiDao, RoundDao roundDao, ServiceObjectMapper serviceObjectMapper, LeagueDao leagueDao, TeamDao teamDao) {
        this.matchApiDao = matchApiDao;
        this.playerApiDao = playerApiDao;
        this.roundDao = roundDao;
        this.mapper = serviceObjectMapper;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;
    }

    @Override
    public String submitNewMatch(MatchPOJO matchPOJO) {

        //check if round exist get round
        Round round = roundDao.findByRoundIdAndRoundCloseFalse(matchPOJO.getRoundId());
        //if no round exists
        if (round == null) {
            return "there is no rounds today please wait for next round";
        }

        // find team by team Id
        List<Team> teams = teamDao.findAllByTeamId(matchPOJO.getTeamsId());


        //validate Team
        if (validateTeam(teams) != null) {
            return validateTeam(teams);
        }


        //create match
        Match match = mapper.map(matchPOJO, Match.class);
        match.setTeams(teams);
        match.setMatchScore(0L);
        match.setRoundId(round.getRoundId());
        if (matchPOJO.getMatchWinner() != null)
            match.setMatchWinner(matchPOJO.getMatchWinner());
        matchApiDao.save(match);

        return null;
    }

    @Override
    public String submitLeagueChampion(Long playerId) {
        League league = leagueDao.findCurrentLeague();


        //if there is a champ player with the same league id

        if (playerApiDao.findLeagueChamp(league.getLeagueId()) != null)
            return "the champ is " + playerApiDao.findLeagueChamp(league.getLeagueId()).getPlayerName() + " please wait for the new league";

        Player player = playerApiDao.findByPlayerIdAndLeagueId(playerId, league.getLeagueId());

        if (player == null) {
            return "Player not found";
        }


        //update player
        player.setIsChamp(true);
        playerApiDao.save(player);

        //update and close league
        league.setHasChampion(true);
        league.setIsClosed(true);
        leagueDao.save(league);
        return null;
    }


    private String validateTeam(List<Team> teams) {

        if (teams.size() > 2) {
            return "Match Must have only two Teams";
        }

        if (teams.stream().anyMatch(team -> team.getPlayersByTeamId().size() > 2)) {
            return "Team Players Must not be more than 2";
        }

        return null;
    }
}
