package com.github.aelmod.ssn2.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(Role role) {
        roleRepository.persist(role);
    }

    @Transactional(readOnly = true)
    public Role findOneByName(String name) {
        return roleRepository.findOneByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Role> findBy(RoleSpecification roleSpecification) {
        return roleRepository.findBy(roleSpecification);
    }
}
