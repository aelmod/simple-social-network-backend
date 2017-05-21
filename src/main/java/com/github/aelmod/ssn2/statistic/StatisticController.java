package com.github.aelmod.ssn2.statistic;

import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/statistic")
public class StatisticController {

    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticController(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @PostMapping
    public List<Long> getStatisticPeer(Date date, @CurrentUser User user) {
        return statisticRepository.getMessagesFromCertainTime(date, user);
    }
}
