package com.hust.nhakhoa.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    private int id;
    private String name;
    private String detail;
    private Double price;
    private List<Integer> appointmentList;
}
