package com.github.aelmod.ssn2.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/statistic")
public class StatisticController {

    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticController(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @GetMapping
    public long get() {
        return statisticRepository.getMessagesFromCertainTime(LocalDate.now());
    }
}
