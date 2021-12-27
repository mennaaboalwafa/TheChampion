package com.bankmasr.thechampion.service.impl;

import com.bankmasr.thechampion.domain.entity.*;
import com.bankmasr.thechampion.model.MatchPOJO;
import com.bankmasr.thechampion.model.MatchPOJOResponse;
import com.bankmasr.thechampion.presistence.dao.LeagueDao;
import com.bankmasr.thechampion.presistence.dao.MatchApiDao;
import com.bankmasr.thechampion.presistence.dao.RoundDao;
import com.bankmasr.thechampion.presistence.dao.TeamDao;
import com.bankmasr.thechampion.presistence.repository.LeagueRoundsRepository;
import com.bankmasr.thechampion.service.MatchApiService;
import com.bankmasr.thechampion.service.mapper.ServiceObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchApiServiceImpl implements MatchApiService {
    private final TeamDao teamDao;
    private final MatchApiDao matchApiDao;
    private final ServiceObjectMapper mapper;
    private final RoundDao roundDao;
    private final LeagueDao leagueDao;
    private final LeagueRoundsRepository leagueRoundsRepository;

    public MatchApiServiceImpl(TeamDao teamDao, MatchApiDao matchApiDao, ServiceObjectMapper mapper, RoundDao roundDao, LeagueDao leagueDao, LeagueRoundsRepository leagueRoundsRepository) {
        this.teamDao = teamDao;
        this.matchApiDao = matchApiDao;
        this.mapper = mapper;
        this.roundDao = roundDao;
        this.leagueDao = leagueDao;
        this.leagueRoundsRepository = leagueRoundsRepository;
    }


    private List<MatchPOJO> createListOfMatches() {
        // auto generate first round  and max 3 matches to first round
        // return matches

        List<Match> createdMatches = new ArrayList<>();
        List<Team> matchTeams = new ArrayList<>();
        Round round;
        //if (roundDao.findAllByDate(LocalDate.now()).isEmpty()) {
        List<Team> teamList = teamDao.getAllByMatchIdNull();
        //get round list by day if  empty create round
        round = new Round();
        roundDao.save(round);
        //create matches
        //create 3 matches by defualt
        for (int i = 0; i < 3; i++) {
            Match match = new Match();
         //   match.setRoundByRoundId(round);
            matchApiDao.save(match);
            createdMatches.add(match);
        }


        for (int j = 0; j < teamList.size(); j++) {
            if (teamList.get(j).getMatchByMatchId() == null && teamList.get(j + 1).getMatchByMatchId() == null) {

                if (j > 0) {
                    j = j - 1;
                }
                if (j > 2) {
                    break;
                }
                teamList.get(j).setMatchByMatchId(createdMatches.get(j));
                teamList.get(j + 1).setMatchByMatchId(createdMatches.get(j));
                matchTeams.add(teamList.get(j));
                matchTeams.add(teamList.get(j + 1));
                //save team
                teamDao.saveAll(matchTeams);
            }


        }
        return mapper.mapList(matchApiDao.findAll(), MatchPOJO.class);
    }


    @Override
    public List<MatchPOJOResponse> getListOfMatches() {
        List<MatchPOJOResponse> matchPOJOList=null;
        // get current league
        League league =leagueDao.findCurrentLeague();
        if(league==null){
            MatchPOJOResponse matchPOJO= new MatchPOJOResponse();
            matchPOJO.setErrorMessage("No current Opened league Found");
            List<MatchPOJOResponse> matchPOJOS= new ArrayList<>();
            matchPOJOS.add(matchPOJO);
            return matchPOJOS;
        }
        //Get the first-round
        LeagueRounds leagueRounds = leagueRoundsRepository.findFirstByLeagueId(league.getLeagueId());
        if (leagueRounds!=null){
             matchPOJOList= mapper.mapMatchToMatchPojoList(matchApiDao.findAllByRoundId(leagueRounds.getRoundId()));
        }

        return matchPOJOList;
    }

    @Override
    public boolean closeTheRound(long roundId) {
        Round round = roundDao.findByRoundId(roundId);
        if (round != null && round.getRoundClose().equals(false)) {
            round.setRoundClose(true);
        }
        return roundDao.save(round) != null;
    }


    @Override
    public String updateMatchWinner(Long matchId, Long roundId, Long teamWinnerId) {
        //check if match exist and in current opened round
        Round round= roundDao.findByRoundIdAndRoundCloseFalse(roundId);
          if (round==null){
              return " round is closed";
          }
          Match match= matchApiDao.findByMatchId(matchId);
          if (match ==null){
              return "match not found";
          }

          match.setMatchWinner(teamWinnerId);
          matchApiDao.save(match);
          Team winnerTeam=teamDao.getById(teamWinnerId);
          winnerTeam.setWinner(true);
          teamDao.save(winnerTeam);


        return null;
    }


}
