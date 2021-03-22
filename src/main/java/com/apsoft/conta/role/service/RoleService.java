package com.apsoft.conta.role.service;

import com.apsoft.conta.role.persistence.Role;
import com.apsoft.conta.role.repository.RoleRepository;
import com.apsoft.conta.user.persistence.User;
import com.apsoft.conta.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void updateRoleToUser(long role_id, long user_id) {
        log.info("update");
        roleRepository.updateRoleToUser(role_id, user_id);
    }






}
