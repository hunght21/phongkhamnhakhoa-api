package com.hust.nhakhoa.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {


    private Integer employeeId;

    private Integer patientId;

    private Integer doctorId;

    private Date startTime;

    private Time endTime;

    private String notes;

    private String status;

    private List<Integer> serviceIds;
}
