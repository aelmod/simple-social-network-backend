package com.github.aelmod.ssn2.statistic;

import com.github.aelmod.ssn2.BaseRepository;
import com.github.aelmod.ssn2.conversation.message.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public class StatisticRepository extends BaseRepository<Message, Integer> {

    public StatisticRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public long getMessagesFromCertainTime(LocalDate date) {
        return entityManager.createQuery("SELECT COUNT(message) FROM Message AS message WHERE message.creationTime >= :date", Long.class)
                .setParameter("date", Date.valueOf(date))
                .getSingleResult();
    }
}
