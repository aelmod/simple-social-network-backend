package com.github.aelmod.ssn2.seed;

import com.github.aelmod.ssn2.role.Role;
import com.github.aelmod.ssn2.role.RoleService;
import com.github.aelmod.ssn2.role.privilege.Privilege;
import com.github.aelmod.ssn2.role.privilege.PrivilegeService;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class FirstSeed {

    private final PrivilegeService privilegeService;

    private final RoleService roleService;

    private final UserService userService;

    public FirstSeed(PrivilegeService privilegeService, RoleService roleService, UserService userService) {
        this.privilegeService = privilegeService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        initRoles();
        initAdminUser();
    }

    private void initRoles() {
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_USER", adminPrivileges);
        createRoleIfNotFound("ROLE_ADMIN", Collections.singletonList(readPrivilege));
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        try {
            return privilegeService.findOneByName(name);
        } catch (EntityNotFoundException e) {
            Privilege privilege = new Privilege(name);
            privilegeService.save(privilege);
            return privilege;
        }
    }

    private Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {
        try {
            return roleService.findOneByName(name);
        } catch (EntityNotFoundException e) {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            roleService.save(role);
            return role;
        }
    }

    private void initAdminUser() {
        User user = new User();
        user.setFullName("admin");
        user.setEmail("admin@icloud.com");
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRoles(Collections.singleton(roleService.findOneByName("ROLE_ADMIN")));
        try {
            userService.getUserByUsername("admin");
        } catch (EntityNotFoundException e) {
            userService.save(user);
        }
    }
}
