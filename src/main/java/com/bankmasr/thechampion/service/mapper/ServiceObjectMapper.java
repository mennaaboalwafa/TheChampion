package com.bankmasr.thechampion.service.mapper;

import com.bankmasr.thechampion.domain.entity.Match;
import com.bankmasr.thechampion.domain.entity.Team;
import com.bankmasr.thechampion.model.MatchPOJOResponse;
import com.bankmasr.thechampion.model.PlayerPOJO;
import com.bankmasr.thechampion.model.TeamPOJO;
import com.bankmasr.thechampion.presistence.dao.TeamDao;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceObjectMapper {
    private final ModelMapper mapper = getModelMapper();
    private final TeamDao teamDao;

    public ServiceObjectMapper(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    private ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD).setAmbiguityIgnored(true);
        mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));
        return mapper;
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ArrayList<T> result = new ArrayList<>();
        for (S element : source) {
            result.add(mapper.map(element, targetClass));
        }
        return result;
    }

    public List<MatchPOJOResponse> mapMatchToMatchPojoList(List<Match> matches) {

        List<MatchPOJOResponse> matchPOJOS = new ArrayList<>();
        matches.forEach(match -> {
            List<TeamPOJO> teamPOJOS = mapList(match.getTeams(), TeamPOJO.class);
            TeamPOJO team = map(teamDao.getById(match.getMatchWinner()), TeamPOJO.class);
            matchPOJOS.add(new MatchPOJOResponse(teamPOJOS, team, match));
        });


        return matchPOJOS;
    }


    public List<TeamPOJO> mapTeamToTeamPOJO(List<Team> teams) {

        return teams.stream().map(team -> {

            TeamPOJO teamResponsePOJO = new TeamPOJO();
            teamResponsePOJO.setTeamId(team.getTeamId());
            teamResponsePOJO.setTeamName(team.getTeamName());
            teamResponsePOJO.setTeamScore(team.getTeamScore());
            teamResponsePOJO.setIsWinner(team.getWinner());
            // get and map users (team members) data
            if (team.getPlayersByTeamId() != null) {
                List<PlayerPOJO> playerPOJOS = team.getPlayersByTeamId().stream().map(teamPlayer -> map(teamPlayer, PlayerPOJO.class)).collect(Collectors.toList());
                teamResponsePOJO.setPlayerPOJO(playerPOJOS);
            }
            return teamResponsePOJO;
        }).collect(Collectors.toList());

    }
}
