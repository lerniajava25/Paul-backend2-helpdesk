package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {

            User user = new User("user", passwordEncoder.encode("userTest"), Role.USER);
            userRepository.save(user);

            User agent = new User("agent", passwordEncoder.encode("agentTest"), Role.AGENT);
            userRepository.save(agent);

            User admin = new User("admin", passwordEncoder.encode("adminTest"), Role.ADMIN);
            userRepository.save(admin);
        }
    }

}
