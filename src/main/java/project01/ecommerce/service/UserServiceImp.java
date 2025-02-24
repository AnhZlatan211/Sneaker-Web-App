package project01.ecommerce.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project01.ecommerce.model.User;
import project01.ecommerce.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        User userNew = new User(user.getUsername(), user.getFullName(), user.getAge(), user.getGender(), user.getRole(), passwordEncoder.encode(user.getPassword()), true);
        userRepository.save(userNew);
    }

    @Override
    public boolean existUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByNameContaining(String keyword) {
        return userRepository.findByFullNameContaining(keyword);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public Long countTotalUsers() {
        return userRepository.count();
    }

}
