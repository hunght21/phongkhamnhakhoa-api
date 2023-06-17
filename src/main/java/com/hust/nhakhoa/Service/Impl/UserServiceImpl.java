package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.UserDTO;
import com.hust.nhakhoa.Exceptions.EmailNotAvailableException;
import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Role;
import com.hust.nhakhoa.Model.Users;
import com.hust.nhakhoa.Repository.RoleRepository;
import com.hust.nhakhoa.Repository.UserRepository;
import com.hust.nhakhoa.Request.UserRequest;
import com.hust.nhakhoa.Service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository repository;

    private Users getUserOrThrow(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", id));
    }

    private void checkEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailNotAvailableException(email);
        }
    }

//    @Override
//    public UserDTO addUser(UserRequest userRequest) {
//        checkEmailUniqueness(userRequest.getEmail());
//        Users user = new Users();
//        user.setEmail(userRequest.getEmail());
//
//        Role role = repository.findById(userRequest.getRole())
//                .orElseThrow(() ->new ResourceNotFoundException("Patient", "patient id", userRequest.getRole()));
//
//        user.setRole(role);
//        user.setImg(userRequest.getImg());
//        user.setName(userRequest.getUserName());
//        user.setGender(userRequest.isGender());
//        user.setPhone(userRequest.getPhone());
//        userRequest.setPassWord(passwordEncoder.encode(userRequest.getPassWord()));
//        user = modelMapper.map(userRequest, Users.class);
//        user = userRepository.save(user);
//
//        return modelMapper.map(user, UserDTO.class);
//    }

    @Override
    public UserDTO updateUser(Integer userId, UserRequest userRequest) {
        Users user = getUserOrThrow(userId);


        Role role = repository.findById(userRequest.getRole())
                .orElseThrow(() ->new ResourceNotFoundException("Role", "role id", userRequest.getRole()));

        if (!user.getEmail().equals(userRequest.getEmail())) checkEmailUniqueness(userRequest.getEmail());

        user.setEmail(userRequest.getEmail());
        user.setRole(role);
 //       user.setImg(userRequest.getImg());
        user.setName(userRequest.getUserName());
        user.setGender(userRequest.isGender());
        user.setPhone(userRequest.getPhone());
//        user.setRoleList(userRequest.getRoleList().stream()
//                .map(roleName -> {
//                            try {
//                                return Role.valueOf(roleName);
//                            } catch (IllegalArgumentException e) {
//                                throw new IllegalArgumentException("Invalid role: " + roleName);
//                            }
//                        })
//                .collect(Collectors.toList()));

        if (userRequest.getPassWord() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassWord()));
        }
        user = userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        Users user = getUserOrThrow(userId);
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public UserDTO getUserById(Integer userId) {
        Users user = getUserOrThrow(userId);
        return modelMapper.map(user, UserDTO.class);
    }


}
