package com.bankmasr.thechampion.presistence.dao.impl;

import com.bankmasr.thechampion.domain.entity.Match;
import com.bankmasr.thechampion.presistence.dao.MatchApiDao;
import com.bankmasr.thechampion.presistence.repository.MatchApiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchApiDaoImpl implements MatchApiDao {
    private final MatchApiRepository matchApiRepository;

    public MatchApiDaoImpl(MatchApiRepository matchApiRepository) {
        this.matchApiRepository = matchApiRepository;
    }

    @Override
    public Match save(Match match) {
        return matchApiRepository.save(match);
    }

    @Override
    public List<Match> findAll() {
        return matchApiRepository.findAll();
    }

    @Override
    public Match findByMatchId(Long matchId) {
        return matchApiRepository.getById(matchId);
    }


    @Override
    public List<Match> saveAll(List<Match> matches) {
        return matchApiRepository.saveAll(matches);
    }

    @Override
    public List<Match> findAllByRoundId(Long roundId) {
        return matchApiRepository.findAllByRoundId(roundId);
    }
}
