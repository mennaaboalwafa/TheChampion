package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerApiRepository extends JpaRepository<Player, Long> {
    Player findByPlayerStaffId(Long staffId);

    Player findByPlayerIdAndLeagueId(Long id, long playerId);

    Player findByLeagueIdAndIsChampTrue(long leagueId);

    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM   (SELECT * FROM player  where team_id is null\n" +
            "ORDER BY  DBMS_RANDOM.RANDOM) where rownum < 3 \n")
    List<Player> findAllRandom();


    @Query(nativeQuery = true, value = "SELECT COUNT(*) \n" +
            "FROM player \n" +
            "where team_id is null ")
    long count();
}
