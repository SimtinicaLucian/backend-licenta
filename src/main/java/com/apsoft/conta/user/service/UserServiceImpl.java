package com.apsoft.conta.user.service;

import com.apsoft.conta.finance.persistence.Incasari;
import com.apsoft.conta.user.persistence.User;
import com.apsoft.conta.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserFromEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }


    @Override
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional findByTokenReset(String resetToken){
        return userRepository.findByTokenReset(resetToken);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public List<User> searchAll() {
        return userRepository.findAll();
    }
}
