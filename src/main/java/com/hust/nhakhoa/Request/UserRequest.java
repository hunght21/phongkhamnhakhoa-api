package com.hust.nhakhoa.Request;

import com.hust.nhakhoa.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String userName;
    private String email;
    private String passWord;
    private List<Role> roleList;
}
