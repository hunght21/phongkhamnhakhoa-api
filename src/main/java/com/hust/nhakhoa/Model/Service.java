package com.hust.nhakhoa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "service_name")
    private String name;

    @Column(name = "service_time")
    private Integer time;

    @Column(name = "service_detail")
    private String detail;

    @Column(name = "service_price", columnDefinition = "DECIMAL(8, 2)")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private Double price;

    @ManyToMany(mappedBy = "services", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

    public Service(String name, Integer time, Double price) {
        this.name = name;
        this.time = time;
        this.price = price;
    }
}
