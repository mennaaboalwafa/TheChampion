package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchApiRepository extends JpaRepository<Match, Long> {


    List<Match> findAllByRoundId(Long roundId);
}
