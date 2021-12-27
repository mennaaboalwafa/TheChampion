package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueApiRepository extends JpaRepository<League,Long> {

    League findByIsClosedFalse ();
}
