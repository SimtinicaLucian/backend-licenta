package com.apsoft.conta.user.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.role.persistence.Role;
import com.apsoft.conta.user.persistence.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserFromEmail(String email);
    Optional <User> findUserByEmail(String email);
    public Optional<User> findByTokenReset(String resetToken);
    public void save(User user);

    List<User> searchAll();

    void deleteId(long id);
}
