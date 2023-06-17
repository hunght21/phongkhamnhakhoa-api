package com.hust.nhakhoa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)

public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

//    @OneToOne(mappedBy = "user")
//    private Patient patient;

//    @OneToMany(mappedBy = "employee")
//    private List<Appointment> appointmentList;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Column(name = "status", nullable = false)
    private boolean status;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role", nullable = false)
//    private List<Role> roleList = new ArrayList<>();


    public Integer hasRole(Role role) {
        return role.getRole_id();
    }

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "img")
    private String img;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Role role = new Role();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}