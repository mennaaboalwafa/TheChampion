package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {


    List<Team> findAllByMatchByMatchIdIsNull();


    List<Team> findAllByMatchByMatchId_MatchId(long matchId);

    @Override
    List<Team> findAllById(Iterable<Long> longs);
}
