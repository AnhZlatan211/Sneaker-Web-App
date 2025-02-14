package project01.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import project01.ecommerce.model.User;
import project01.ecommerce.repository.UserRepository;

@Configuration
public class CustomCommandLineRunner{
    @Bean
    CommandLineRunner runOnStart() {
        return args -> {
            System.out.println("🚀🚀🚀 Chạy rồi, tuyệt vời! 🚀🚀🚀");
        };
    }
//    @Bean
//    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            if (userRepository.findByUsername("admin@gmail.com").isEmpty()) {
//                User admin = new User();
//                admin.setUsername("admin@gmail.com");
//                admin.setPassword(passwordEncoder.encode("1"));
//                admin.setFullName("Nguyen Duy Anh");
//                admin.setAge("21");
//                admin.setGender("GOD!");
//                admin.setRole("ADMIN");
//                admin.setEnabled(true);
//                System.out.println("✅✅✅ User ADMIN đã được tạo. ✅✅✅");
//            }
//            else {
//                System.out.println("❗❗❗ User ADMIN đã tồn tại, không cần thêm. ❗❗❗");
//            }
//        };
//    }
}
