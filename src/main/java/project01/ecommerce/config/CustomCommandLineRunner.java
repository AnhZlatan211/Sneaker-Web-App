package project01.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomCommandLineRunner{
    @Bean
    CommandLineRunner runOnStart() {
        return args -> {
            System.out.println("🚀🚀🚀 Chạy rồi, tuyệt vời! 🚀🚀🚀");
        };
    }
}
