package com.hust.nhakhoa.Request;

import com.hust.nhakhoa.Model.Role;
import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Integer id;
    private String userName;
    private String email;
    private String passWord;
    private Integer role;
    private boolean gender;
    private Integer phone;
//    private String img;


}
