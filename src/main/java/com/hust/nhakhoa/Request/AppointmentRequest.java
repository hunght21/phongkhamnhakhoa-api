package com.hust.nhakhoa.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

//    @NotNull(message = "Employee's id cannot be null")
    private Integer employeeId;

 //   @NotNull(message = "Patient's id cannot be null")
    private Integer patientId;

    private Integer doctorId;

 //   @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

 //   @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    private String notes;

  //  @NotNull(message = "Status cannot be null")
    private String status;

    private List<Integer> serviceIds;
}
