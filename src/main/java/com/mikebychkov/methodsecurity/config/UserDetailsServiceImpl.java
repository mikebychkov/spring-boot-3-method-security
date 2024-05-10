package com.mikebychkov.methodsecurity.config;

import com.mikebychkov.methodsecurity.dao.User;
import com.mikebychkov.methodsecurity.dao.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo repo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repo.findByUsernameIgnoreCase(username);

        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }
}