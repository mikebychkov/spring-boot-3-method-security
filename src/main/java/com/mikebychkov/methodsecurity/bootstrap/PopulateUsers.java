package com.mikebychkov.methodsecurity.bootstrap;

import com.mikebychkov.methodsecurity.dao.User;
import com.mikebychkov.methodsecurity.dao.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PopulateUsers implements CommandLineRunner {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin", encoder.encode("password"), "admin@mike.com", "ADMIN");
        User user = new User("user", encoder.encode("password"), "user@mike.com", "USER");

        repo.save(admin);
        repo.save(user);

        admin = repo.findByUsername("admin").orElseThrow();
        user = repo.findByUsername("user").orElseThrow();

        log.info("ADMIN: {}", admin);
        log.info("USER: {}", user);
    }
}
