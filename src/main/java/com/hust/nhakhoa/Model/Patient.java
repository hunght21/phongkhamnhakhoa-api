package com.hust.nhakhoa.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//                    property = "id")
public class Patient implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//
//    @Column(name = "patient_name")
//    private String name;
//
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "account_id", nullable = false)
//    private Users account;
//
//
//    private Integer role_id;

    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Appointment> appointmentList;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//  //  @JsonManagedReference
//    private List<Prescription> prescriptionList;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//
//    @Column(nullable = false)
//    @NotBlank(message = "Address cannot be empty")
//    @Size(min = 5, message = "Address must be at least 5 characters long")
//    private String address;
//
//    @Column(name = "date_of_birth")
//    private String dateOfBirth;
//
//    @Column(name = "gender", nullable = false)
//    private boolean gender;

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

    public Integer hasRole(Role role) {
        return role.getRole_id();
    }

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "phone")
    private Integer phone;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Role role = getRole();
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
