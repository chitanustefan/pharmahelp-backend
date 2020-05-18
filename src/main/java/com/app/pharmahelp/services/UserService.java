package com.app.pharmahelp.services;

import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.repository.UserRepo;
import com.app.pharmahelp.security.UserPrincipal;
import com.app.pharmahelp.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {


    @Autowired
    UserRepo userRepository;

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User saveUser(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;

    }
    @Override
    public  Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {

        Optional<User> optionalUser = userRepository.findById(id);
        User result = optionalUser.orElse(user);
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            // Let people login with either username or email
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with email : " + email)
                    );

            return UserPrincipal.create(user);
        }

        @Transactional
        public UserDetails loadUserById(Long id) {
            User user = userRepository.findById(id).orElseThrow(
                    () -> new NullPointerException()
            );

            return UserPrincipal.create(user);
        }

}
