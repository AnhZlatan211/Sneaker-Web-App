package project01.ecommerce.service;

import project01.ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUserName(String username);
    User save(User user);
    void deleteUser(Long id);
    boolean existUserByUsername(String username);
    List<User> getListOfUsers();
    List<User> findByNameContaining(String keyword);
    Long countTotalUsers();
}
