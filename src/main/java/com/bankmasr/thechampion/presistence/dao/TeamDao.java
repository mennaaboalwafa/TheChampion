package com.bankmasr.thechampion.presistence.dao;

import com.bankmasr.thechampion.domain.entity.Team;

import java.util.List;

public interface TeamDao {


    List<Team> getAll();

    Team save(Team team);

    List<Team> saveAll(List<Team> teamList);

    long count();

    List<Team> getAllByMatchIdNull();

    List<Team> findAllByMatchId(Long matchId);

    Team getById(Long winnerTeamId);

    List<Team> findAllByTeamId(List<Long> teamsId);
}
