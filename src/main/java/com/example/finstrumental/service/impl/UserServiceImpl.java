package com.example.finstrumental.service.impl;

import com.example.finstrumental.model.Role;
import com.example.finstrumental.model.Status;
import com.example.finstrumental.model.User;
import com.example.finstrumental.repository.RoleRepository;
import com.example.finstrumental.repository.UserRepository;
import com.example.finstrumental.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role user_role = roleRepository.findByName("USER_ROLE");
        List<Role> roles = new ArrayList<>();
        roles.add(user_role);
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("Method:register - User {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> res = userRepository.findAll();
        log.info("Method: getAll - found {} users", res.size());
        return res;
    }

    @Override
    public User findByUsername(String username) {
        User res = userRepository.findUserByUsername(username);
        log.info("Method: findByUsername - found user {} by username {}", res, username);
        return res;
    }

    @Override
    public User findById(Long id) {
        User res = userRepository.getById(id);
        log.info("Method: findById - found user {} by id {}", res, id);
        return res;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("Method delete -  id: {} successfully deleted", id);
    }}
