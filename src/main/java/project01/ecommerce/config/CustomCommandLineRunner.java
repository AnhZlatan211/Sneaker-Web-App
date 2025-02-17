package project01.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import project01.ecommerce.model.Product;
import project01.ecommerce.model.User;
import project01.ecommerce.repository.ProductRepository;
import project01.ecommerce.repository.UserRepository;

import java.util.List;

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
            if (userRepository.findByUsername("admin@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("1"));
                admin.setFullName("Nguyen Duy Anh");
                admin.setAge("21");
                admin.setGender("GOD!");
                admin.setRole("ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);
                System.out.println("✅✅✅ User ADMIN đã được tạo. ✅✅✅");
            }
            else {
                System.out.println("❗❗❗ User ADMIN đã tồn tại, không cần thêm. ❗❗❗");
            }
        };
    }
    @Bean
    CommandLineRunner initProductList(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                List<Product> products = List.of(
                        new Product("Nike Air Force 1", 120.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/13eaa885-07bd-44da-bf0d-1b2423d4b50d/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("Nike Air Force 2", 120.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/13eaa885-07bd-44da-bf0d-1b2423d4b50d/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("Nike Air Force 3", 120.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/13eaa885-07bd-44da-bf0d-1b2423d4b50d/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("Nike Air Force 4", 120.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/13eaa885-07bd-44da-bf0d-1b2423d4b50d/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("Nike Air Force 5", 120.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/13eaa885-07bd-44da-bf0d-1b2423d4b50d/W+NIKE+V2K+RUN.png", "nothing")
                        );
                productRepository.saveAll(products);
                System.out.println("✅✅✅ Dữ liệu sản phẩm đã được thêm vào database! ✅✅✅");
            }
            else {
                System.out.println("❗❗❗ Sản phẩm đã tồn tại, không cần thêm. ❗❗❗");
            }
        };
    }
}
