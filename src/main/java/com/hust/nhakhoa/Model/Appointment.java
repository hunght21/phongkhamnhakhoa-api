package com.hust.nhakhoa.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Table(name = "appointment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apointment_id")
    private int Id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Users employee;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_td", nullable = false)
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appointments_services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;

    @Column(name = "startTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @Column(name = "endTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Time endTime;

    @Column(columnDefinition = "Text")
    private String notes;

    @Column(columnDefinition = "DECIMAL(8, 2)")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal money;

    @Column(name = "status")
    private String status;




}
