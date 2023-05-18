package com.hust.nhakhoa.Request;

import com.hust.nhakhoa.Model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequet {

    private int id;
    private String name;
    private Integer time;
    private String detail;
    private Double price;
    private List<Integer> appointmentList;
}
