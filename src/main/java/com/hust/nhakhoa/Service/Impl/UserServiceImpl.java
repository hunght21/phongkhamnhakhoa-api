package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.UserDTO;
import com.hust.nhakhoa.Request.UserRequest;
import com.hust.nhakhoa.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO addUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserDTO updateUser(Integer userId, UserRequest userRequest) {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public void checkEmailUniqueness(String email) {

    }
}
