package com.github.aelmod.ssn2.microblog.commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentaryService {

    private final CommentaryRepository commentaryRepository;

    @Autowired
    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    @Transactional
    public void save(Commentary commentary) {
        commentaryRepository.persist(commentary);
    }

    @Transactional(readOnly = true)
    public List<Commentary> findBy(CommentarySpecification commentarySpecification) {
        return commentaryRepository.findBy(commentarySpecification);
    }

    public Commentary findById(int commentaryId) {
        return commentaryRepository.findOneByPk(commentaryId).orElseThrow(EntityNotFoundException::new);
    }
}
