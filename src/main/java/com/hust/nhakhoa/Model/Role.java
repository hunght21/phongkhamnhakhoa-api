package com.hust.nhakhoa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_note")
    private String note;

    public Role(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

}
