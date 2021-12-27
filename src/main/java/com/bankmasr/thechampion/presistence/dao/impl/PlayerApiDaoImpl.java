package com.bankmasr.thechampion.presistence.dao.impl;

import com.bankmasr.thechampion.domain.entity.Player;
import com.bankmasr.thechampion.presistence.dao.PlayerApiDao;
import com.bankmasr.thechampion.presistence.repository.PlayerApiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerApiDaoImpl implements PlayerApiDao {

    private final PlayerApiRepository playerApiRepository;

    public PlayerApiDaoImpl(PlayerApiRepository playerApiRepository) {
        this.playerApiRepository = playerApiRepository;
    }

    @Override
    public Player save(Player player) {
        return playerApiRepository.save(player);
    }

    @Override
    public List<Player> getAll() {
        return playerApiRepository.findAll();
    }

    @Override
    public List<Player> findAll() {
        return playerApiRepository.findAll();
    }


    public List<Player> findRandomPlayers() {

        return playerApiRepository.findAllRandom();
    }

    @Override
    public Player getByStaffId(Long staffId) {
        return playerApiRepository.findByPlayerStaffId(staffId);
    }

    @Override
    public Long countAll() {
        return playerApiRepository.count();
    }

    @Override
    public void saveAll(List<Player> players) {
        playerApiRepository.saveAll(players);
    }

    @Override
    public Player findByPlayerIdAndLeagueId(Long leagueId, Long playerId) {
        return playerApiRepository.findByPlayerIdAndLeagueId(playerId, leagueId);
    }

    @Override
    public Player findLeagueChamp(Long leagueId) {
        return playerApiRepository.findByLeagueIdAndIsChampTrue(leagueId);
    }

    public long count() {
        return playerApiRepository.count();
    }
}
