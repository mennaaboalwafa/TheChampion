package com.bankmasr.thechampion.presistence.dao;

import com.bankmasr.thechampion.domain.entity.League;

public interface LeagueDao {
    void save(League league);

    League findCurrentLeague();
}
