package com.bankmasr.thechampion.presistence.dao.impl;

import com.bankmasr.thechampion.domain.entity.League;
import com.bankmasr.thechampion.presistence.dao.LeagueDao;
import com.bankmasr.thechampion.presistence.repository.LeagueApiRepository;
import org.springframework.stereotype.Service;

@Service
public class LeagueDaoImpl implements LeagueDao {
    private final LeagueApiRepository leagueApiRepository;

    public LeagueDaoImpl(LeagueApiRepository leagueApiRepository) {
        this.leagueApiRepository = leagueApiRepository;
    }

    @Override
    public void save(League league) {
        leagueApiRepository.save(league);
    }

    @Override
    public League findCurrentLeague() {
        return leagueApiRepository.findByIsClosedFalse();
    }
}
