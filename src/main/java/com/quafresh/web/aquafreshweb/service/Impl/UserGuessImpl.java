package com.quafresh.web.aquafreshweb.service.Impl;

import com.quafresh.web.aquafreshweb.entity.User;
import com.quafresh.web.aquafreshweb.repositories.UserRepository;
import com.quafresh.web.aquafreshweb.service.guess.UserServiceGuess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGuessImpl implements UserServiceGuess {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }
}
