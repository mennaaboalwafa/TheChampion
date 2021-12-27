package com.bankmasr.thechampion.presistence.dao.impl;

import com.bankmasr.thechampion.domain.entity.Team;
import com.bankmasr.thechampion.presistence.dao.TeamDao;
import com.bankmasr.thechampion.presistence.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamImpl implements TeamDao {
    private final TeamRepository teamRepository;

    public TeamImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }


    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> saveAll(List<Team> teamList) {
        return teamRepository.saveAll(teamList);
    }

    @Override
    public long count() {
        return teamRepository.count();
    }

    @Override
    public List<Team> getAllByMatchIdNull() {
        return teamRepository.findAllByMatchByMatchIdIsNull();
    }

    @Override
    public List<Team> findAllByMatchId(Long matchId) {
        return teamRepository.findAllByMatchByMatchId_MatchId(matchId);
    }

    @Override
    public Team getById(Long winnerTeamId) {
        return teamRepository.getById(winnerTeamId);
    }

    @Override
    public List<Team> findAllByTeamId(List<Long> teamsId) {
        return teamRepository.findAllById(teamsId);
    }

}
