package com.bankmasr.thechampion.presistence.dao.impl;

import com.bankmasr.thechampion.domain.entity.Round;
import com.bankmasr.thechampion.presistence.dao.RoundDao;
import com.bankmasr.thechampion.presistence.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class RoundImpl implements RoundDao {
    private final RoundRepository roundRepository;

    public RoundImpl(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }


    @Override
    public Round save(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public Round findAllByDate(LocalDate from) {
        LocalDateTime localDate = LocalDate.now().atStartOfDay();
        LocalDateTime endOfTheDay = localDate.with(LocalTime.MAX);
        return roundRepository.findByCreatedAtBetween(Timestamp.valueOf(localDate), Timestamp.valueOf(endOfTheDay));
    }

    @Override
    public Round findByFirst() {
        return roundRepository.findFirstByAndAndRoundCloseFalse();
    }

    @Override
    public Round findByRoundIdAndRoundCloseFalse(long roundId) {
        return roundRepository.findByRoundIdAndRoundCloseFalse(roundId);
    }

    @Override
    public Round findByRoundId(long roundId) {
        return roundRepository.getById(roundId);
    }


}
