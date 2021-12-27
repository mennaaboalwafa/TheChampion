package com.bankmasr.thechampion.presistence.dao;

import com.bankmasr.thechampion.domain.entity.Round;

import java.time.LocalDate;

public interface RoundDao {

    Round save(Round round);


    Round findAllByDate(LocalDate from);

    Round findByFirst();

    Round findByRoundIdAndRoundCloseFalse(long roundId);

    Round findByRoundId(long roundId);
}
