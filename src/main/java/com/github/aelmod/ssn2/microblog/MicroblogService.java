package com.github.aelmod.ssn2.microblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MicroblogService {

    private final MicroblogRepository microblogRepository;

    @Autowired
    public MicroblogService(MicroblogRepository microblogRepository) {
        this.microblogRepository = microblogRepository;
    }

    @Transactional(readOnly = true)
    public List<Microblog> findBy(MicroblogSpecification microblogSpecification) {
        return microblogRepository.findBy(microblogSpecification);
    }

    @Transactional(readOnly = true)
    public Microblog getByPk(Integer id) {
        return microblogRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Microblog microblog) {
        microblogRepository.persist(microblog);
    }
}
