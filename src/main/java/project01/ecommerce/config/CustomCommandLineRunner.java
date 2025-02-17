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
                        new Product("Adidas Yeezy Boost 350", 250.0, 30, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/c4ebefeb-5ddf-4b4f-b6e5-e8bbd94cfada/NIKE+V2K+RUN.png", "nothing"),
                        new Product("Nike Dunk Low", 110.0, 40, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/f5b0a145-18ba-4554-a861-f0c3a504ca28/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("Adidas Superstar", 90.0, 25, "https://www.nike.com/vn/u/custom-v2k-by-you-10001813/4086445724", "nothing"),
                        new Product("Puma RS-X", 130.0, 20, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/385e4c7b-697a-4537-84c3-80f1f2e50c14/W+NIKE+V2K+RUN.png", "nothing"),
                        new Product("New Balance 550", 140.0, 35, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/eaf524f7-a9f7-4f70-a438-1b0480eb2540/NIKE+COURT+VISION+LO.png", "nothing"),
                        new Product("Jordan 1 Retro High", 170.0, 15, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/a42a5d53-2f99-4e78-a081-9d07a2d0774a/AIR+FORCE+1+%2707.png", "nothing"),
                        new Product("Nike Air Max 97", 160.0, 22, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/60ba7d78-b4f1-4f5a-9be6-79872a95689f/NIKE+REVOLUTION+7+WIDE.png", "nothing"),
                        new Product("Adidas NMD R1", 140.0, 30, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/fa52cda4-2179-4f0d-a17b-83d3e8fe81b0/NIKE+VISTA+SANDAL.png", "nothing"),
                        new Product("Reebok Club C 85", 80.0, 28, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/0dde9946-f6fd-4a13-a149-1c2f11ca0b11/AIR+JORDAN+1+MID.png", "nothing"),
                        new Product("Vans Old Skool", 70.0, 50, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/e3cc2305-65bb-4824-b4bd-9474386f6656/WMNS+NIKE+P-6000.png", "nothing"),
                        new Product("Nike Blazer Mid 77", 110.0, 33, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/e21e9282-d220-42d7-974d-873e5736f598/PEGASUS+PLUS.png", "nothing"),
                        new Product("Adidas Ultraboost 22", 180.0, 27, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/6a5b3755-fca5-4fbf-af97-a0a5f9ff028d/NIKE+VICTORI+ONE+SLIDE.png", "nothing"),
                        new Product("Converse Chuck Taylor", 60.0, 40, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/4a432d07-0f21-41ca-8622-e55fd5012049/NIKE+CALM+SLIDE.png", "nothing"),
                        new Product("Nike Air Jordan 4", 190.0, 14, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/df01fd1c-e04b-4836-a9d1-cd7f4221fc74/NIKE+JAM.png", "nothing"),
                        new Product("Asics Gel-Kayano 29", 160.0, 18, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/641c2ad1-800d-469a-9b5b-55b69ec6be60/PHANTOM+LUNA+II+ELITE+FG.png", "nothing"),
                        new Product("Under Armour HOVR Phantom", 140.0, 19, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/f71f58eb-ad7b-4495-acd0-163411f79298/ZM+SUPERFLY+10+ELITE+FG.png", "nothing"),
                        new Product("Nike Pegasus 40", 130.0, 26, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/4b5cc7fb-f898-4f0d-8052-b98da0010329/JORDAN+LUKA+3+PF.png", "nothing"),
                        new Product("Salomon XT-6", 180.0, 17, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/c15b1e56-3eb4-4745-ae33-b38b9851146d/AIR+JORDAN+1+LOW+SE.png", "nothing"),
                        new Product("Mizuno Wave Rider 25", 140.0, 21, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/df5929be-5478-46e6-9e36-15e1ed9e401a/AIR+JORDAN+4+RM.png", "nothing"),
                        new Product("Fila Disruptor 2", 90.0, 23, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/b99c1521-3566-47dc-bd39-17a37b62602c/G.T.+HUSTLE+3+FP+EP.png", "nothing"),
                        new Product("Nike Kobe 6 Protro", 200.0, 12, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/04af6d78-2200-43f8-ba98-07127d500872/NIKE+V2K+RUN.png", "nothing"),
                        new Product("Puma Clyde OG", 100.0, 30, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/890d1a70-dccc-46f3-9c92-1f7c340d2a6b/NIKE+AIR+ZOOM+G.T.+JUMP+2+OLY.png", "nothing"),
                        new Product("New Balance 2002R", 150.0, 24, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/ccd2355a-c885-46c7-b8bd-31e69f33fa2e/AIR+MORE+UPTEMPO+SLIDE.png", "nothing"),
                        new Product("Jordan 11 Retro", 210.0, 16, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/9fe70154-e4bb-4bcc-9cf0-2efda240916c/NIKE+ZOOMX+VAPORFLY+NEXT%25+3.png", "nothing"),
                        new Product("Nike Zoom Freak 4", 130.0, 20, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/e61080a2-fbe3-4bd9-917b-673bb10f6c73/AIR+MAX+DN+OLY.png", "nothing"),
                        new Product("Adidas Forum Low", 110.0, 32, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/202d540b-5aa9-4d60-9c9a-86475b81d34d/KILLSHOT+2+LEATHER.png", "nothing"),
                        new Product("Hoka One One Clifton 9", 140.0, 18, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/97a78eda-5326-43dc-9db5-78ac69ec8bac/BOOK+1+EP.png", "nothing"),
                        new Product("Nike Vapormax Plus", 210.0, 14, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/8b0d4d2e-d306-4b50-b335-e9c8202144d8/NIKE+DUNK+LOW+SE.png", "nothing"),
                        new Product("Reebok Zig Kinetica", 120.0, 29, "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/9f55d934-bac4-4cb3-8d67-bb6c71121d03/AIR+FORCE+1+%2707.png", "nothing")
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
