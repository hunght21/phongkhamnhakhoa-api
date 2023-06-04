package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.DTO.UserDTO;
import com.hust.nhakhoa.Request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserDTO addUser(UserRequest userRequest);
    public UserDTO updateUser(Integer userId,UserRequest userRequest);
    public void deleteUser(Integer userId);
    public UserDTO getUserById(Integer userId);

}
