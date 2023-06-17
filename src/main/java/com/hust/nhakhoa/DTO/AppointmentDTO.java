package com.hust.nhakhoa.DTO;

import com.hust.nhakhoa.Model.Doctor;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Service;
import com.hust.nhakhoa.Model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO implements Serializable {

    private int id;
    private UserDTO user;
    private Patient patient;
    private Doctor doctor;
    private Date startTime;
    private Time endTime;
    private String notes;
    private String status;
    private List<Service> servicesId;

}
