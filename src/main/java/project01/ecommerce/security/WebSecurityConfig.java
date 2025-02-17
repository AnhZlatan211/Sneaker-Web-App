package project01.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import project01.ecommerce.security.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((c) -> c.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "signup", "/", "/error").permitAll()
                        .requestMatchers("/css/**","/img/**").permitAll()
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );
        return http.build();
    }
}
