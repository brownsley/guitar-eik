package com.chord.server.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chord.server.dto.request.auth.UserCreateDto;
import com.chord.server.dto.request.auth.UserLoginDto;
import com.chord.server.entities.auth.User;
import com.chord.server.exception.ResourceAlreadyExistsException;
import com.chord.server.exception.ResourceNotFoundException;
import com.chord.server.repositories.auth.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public String register(UserCreateDto createDto) {
        if (userRepository.existsByEmployeeId(createDto.getEmployeeId())) {
            throw new ResourceAlreadyExistsException("Employee ID is already registered!");
        }
        User user = new User();
        user.setEmployeeId(createDto.getEmployeeId());
        user.setName(createDto.getUsername());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        userRepository.save(user);
        String token = jwtService.generateToken(createDto.getEmployeeId());
        return token;
    }

    public String login(UserLoginDto loginDto) {
        User user = userRepository.findByEmployeeId(loginDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with Employee ID: " + loginDto.getEmployeeId()));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }
        return jwtService.generateToken(user.getEmployeeId());
    }
}
