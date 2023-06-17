package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private int id;
    private String name;
    private String mail;
    private boolean gender;
    private Integer phone;
    private String img;
    private boolean status;
    private Integer role;
}
