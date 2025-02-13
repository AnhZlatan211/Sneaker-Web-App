package project01.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project01.ecommerce.model.User;
import project01.ecommerce.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        User userNew = new User(user.getUsername(), user.getFullName(), user.getAge(), user.getGender(), user.getRole(), passwordEncoder.encode(user.getPassword()), true);
        return userRepository.save(userNew);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
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
    public Long countTotalUsers() {
        return userRepository.countTotalUsers();
    }

}
