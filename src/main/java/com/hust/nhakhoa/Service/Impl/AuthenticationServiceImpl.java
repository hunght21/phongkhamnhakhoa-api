package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.Exceptions.EmailNotAvailableException;
import com.hust.nhakhoa.Model.Role;
import com.hust.nhakhoa.Model.Users;
import com.hust.nhakhoa.Repository.UserRepository;
import com.hust.nhakhoa.Request.AuthenticationRequest;
import com.hust.nhakhoa.Request.RegisterRequest;
import com.hust.nhakhoa.Response.JwtAuthResponse;
import com.hust.nhakhoa.Service.AuthenticationService;
import com.hust.nhakhoa.Service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final  JwtService jwtService;

    private final  AuthenticationManager authenticationManager;

    @Override
    public JwtAuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailNotAvailableException(registerRequest.getEmail());
        }

        Users user = Users.builder()
                .name(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roleList(List.of(Role.EMPLOYEE))
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    @Override
    public JwtAuthResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        Users user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
