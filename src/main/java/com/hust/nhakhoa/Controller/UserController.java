package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.DTO.UserDTO;
import com.hust.nhakhoa.Request.UserRequest;
import com.hust.nhakhoa.Service.AppointmentService;
import com.hust.nhakhoa.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController implements SecuredController{

    @Autowired
    private UserService userService;

 //   @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
//    @PostMapping("/add")
//    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserRequest userRequest) {
//        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
//    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody @Valid UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("delete{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
