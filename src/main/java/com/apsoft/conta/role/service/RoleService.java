package com.apsoft.conta.role.service;

import com.apsoft.conta.role.persistence.Role;
import com.apsoft.conta.role.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void deleteRole(long user_id, long role_id) {
        log.info("delete");
        roleRepository.deleteRoleBytrews(user_id, role_id);
    }

    public void addRoleToUser(long user_id, long role_id) {
        log.info("add");
        roleRepository.addRoleToUser(user_id, role_id);
    }

    public List<Collection> viewAllRoles(){
        return roleRepository.showAllRole();
    }

}
