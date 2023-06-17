package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.PatientDTO;
import com.hust.nhakhoa.Exceptions.EmailNotAvailableException;
import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Role;
import com.hust.nhakhoa.Model.Users;
import com.hust.nhakhoa.Repository.PatientRepository;
import com.hust.nhakhoa.Repository.RoleRepository;
import com.hust.nhakhoa.Repository.UserRepository;
import com.hust.nhakhoa.Request.AuthenticationRequest;
import com.hust.nhakhoa.Request.RegisterPatientRequest;
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

    private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    private final  JwtService jwtService;

    private final  AuthenticationManager authenticationManager;

    private final RoleRepository repository;

    @Override
    public JwtAuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailNotAvailableException(registerRequest.getEmail());
        }
        Role role = repository.findById(registerRequest.getRole())
                .orElseThrow(() ->new ResourceNotFoundException("Role", "role id", registerRequest.getRole()));


        Users user = Users.builder()
                .name(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .gender(registerRequest.isGender())
                .role(role)
                .build();

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public JwtAuthResponse registerPatient(RegisterPatientRequest registerPatientRequest) {
        if (patientRepository.existsByEmail(registerPatientRequest.getEmail())) {
            throw new EmailNotAvailableException(registerPatientRequest.getEmail());
        }
        Role role = repository.findById(registerPatientRequest.getRole())
                .orElseThrow(() ->new ResourceNotFoundException("Role", "role id", registerPatientRequest.getRole()));
        Patient patient = Patient.builder()
                .name(registerPatientRequest.getUserName())
                .email(registerPatientRequest.getEmail())
                .password(passwordEncoder.encode(registerPatientRequest.getPassword()))
                .phone(registerPatientRequest.getPhone())
                .gender(registerPatientRequest.isGender())
                .role(role)
                .build();

        patientRepository.save(patient);
        String jwtToken = jwtService.generateToken(patient);
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

//        Users user = userRepository.findByEmail(authenticationRequest.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


//
        String jwtToken;
//        if(user != null)
//        {
//            jwtToken = jwtService.generateToken(user);
//        }else {
            Patient patient = patientRepository.findByEmail(authenticationRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Patient not found"));
            jwtToken = jwtService.generateToken(patient);
//       }
        return JwtAuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
