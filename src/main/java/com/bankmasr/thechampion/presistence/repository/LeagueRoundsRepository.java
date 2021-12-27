package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.LeagueRounds;
import com.bankmasr.thechampion.domain.entity.LeagueRoundsPK;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueRoundsRepository extends JpaRepository <LeagueRounds, LeagueRoundsPK> {
    LeagueRounds findFirstByLeagueId (long leagueId);
}
