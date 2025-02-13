package project01.ecommerce.service;

import project01.ecommerce.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    boolean existUserByUsername(String username);
    List<User> getListOfUsers();
    void deleteUser(Long id);
    List<User> findByNameContaining(String keyword);
    Long countTotalUsers();
}
