package com.apsoft.conta.role.controller;


import com.apsoft.conta.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/user/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role/add/{user_id}/{role_id}")
    public void addRoleToUser(@PathVariable long user_id, @PathVariable long role_id){
        roleService.addRoleToUser(user_id, role_id);
    }

    @DeleteMapping("/role/delete/{user_id}/{role_id}")
    public void deleteTole(@PathVariable long user_id, @PathVariable long role_id){
        roleService.deleteRole(user_id,role_id);
    }

    @GetMapping("role/viewAll")
    public List<Collection> viewAll(){
        return roleService.viewAllRoles();
    }
}
