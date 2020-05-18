package com.app.pharmahelp.services.interfaces;

import com.app.pharmahelp.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Long id);
    List<User> getAllUsers();
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
