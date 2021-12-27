package com.bankmasr.thechampion.presistence.dao;

import com.bankmasr.thechampion.domain.entity.Match;
import com.bankmasr.thechampion.domain.entity.Round;

import java.util.List;

public interface MatchApiDao {
    Match save(Match match);

    List<Match> findAll();

    Match findByMatchId(Long matchId);

    List<Match> saveAll(List<Match> matches);

    List<Match> findAllByRoundId(Long roundId);
}
