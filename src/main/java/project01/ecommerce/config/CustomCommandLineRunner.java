package project01.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import project01.ecommerce.repository.UserRepository;

@Configuration
public class CustomCommandLineRunner{
    @Bean
    CommandLineRunner runOnStart() {
        return args -> {
            System.out.println("🚀🚀🚀 Chạy rồi, tuyệt vời! 🚀🚀🚀");
        };
    }
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("🚀🚀🚀 Chạy rồi, tuyệt vời! 🚀🚀🚀");
        };
    }
}
