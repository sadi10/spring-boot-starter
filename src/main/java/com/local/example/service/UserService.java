package com.local.example.service;

;
import com.local.example.entity.User;
import com.local.example.model.UserDTO;
import com.local.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(userDTO.isEnabled());
        user.setAuthorities(userDTO.getAuthorities());

        return userRepository.save(user);
    }
}
