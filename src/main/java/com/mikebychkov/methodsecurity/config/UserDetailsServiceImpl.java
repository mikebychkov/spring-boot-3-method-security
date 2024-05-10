package com.mikebychkov.methodsecurity.config;

import com.mikebychkov.methodsecurity.dao.User;
import com.mikebychkov.methodsecurity.dao.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo repository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUsernameIgnoreCase(username);

        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }
}