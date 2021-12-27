package com.bankmasr.thechampion.presistence.repository;

import com.bankmasr.thechampion.domain.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {

//    @Query(nativeQuery = true, value = "select round_id from round  where to_date(to_char(created_at),'yyyy/MM/dd'))= :status ")
//    long findByCreatedAtEquals(@Param("status") LocalDate localDate);





    Round findFirstByAndAndRoundCloseFalse();

    Round findByCreatedAtBetween(Timestamp valueOf, Timestamp valueOf1);
    Round findByRoundIdAndRoundCloseFalse(long roundId);
}
