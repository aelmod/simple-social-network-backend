package com.github.aelmod.ssn2.role.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    public void save(Privilege privilege) {
        privilegeRepository.persist(privilege);
    }

    @Transactional(readOnly = true)
    public Privilege findOneByName(String name) {
        return privilegeRepository.findOneByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
