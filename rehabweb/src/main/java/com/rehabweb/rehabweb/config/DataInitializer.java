package com.rehabweb.rehabweb.config;

import com.rehabweb.rehabweb.entity.User;
import com.rehabweb.rehabweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initializeData(@Autowired UserRepository userRepository,
                                            @Autowired BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // 관리자 계정 생성 (이미 있으면 생성하지 않음)
            if (!userRepository.existsByUsername("krias77")) {
                User admin = new User();
                admin.setUsername("krias77");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole(User.Role.ADMIN);
                admin.setEnabled(true);
                
                userRepository.save(admin);
                System.out.println("✅ Admin account created: krias77 / 1234");
            }
        };
    }
}
