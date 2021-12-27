package com.bankmasr.thechampion.presistence.dao;

import com.bankmasr.thechampion.domain.entity.Player;

import java.util.List;

public interface PlayerApiDao {


    Player save(Player player);

    List<Player> getAll();

    List<Player> findAll();

    Player getByStaffId (Long staffId);

    List<Player> findRandomPlayers();
    Long countAll();

    void saveAll(List<Player> players);

    Player findByPlayerIdAndLeagueId(Long id, Long playerId);

    Player findLeagueChamp(Long leagueId);

    long count();
}
