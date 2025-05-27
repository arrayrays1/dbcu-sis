package com.academic.management.service;

import com.academic.management.dto.LoginRequest;
import com.academic.management.dto.UserDto;
import com.academic.management.model.User;
import com.academic.management.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public UserDto login(LoginRequest loginRequest, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setEmail(user.getEmail());
                userDto.setRole(user.getRole());
                
                // Store user in session
                session.setAttribute("user", userDto);
                
                return userDto;
            }
        }
        
        return null;
    }
    
    public void logout(HttpSession session) {
        session.invalidate();
    }
    
    public UserDto getCurrentUser(HttpSession session) {
        return (UserDto) session.getAttribute("user");
    }
}
