package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.Request.AuthenticationRequest;
import com.hust.nhakhoa.Request.RegisterPatientRequest;
import com.hust.nhakhoa.Request.RegisterRequest;
import com.hust.nhakhoa.Response.JwtAuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public JwtAuthResponse register(RegisterRequest registerRequest);

    public  JwtAuthResponse registerPatient(RegisterPatientRequest registerPatientRequest);
    public JwtAuthResponse authenticate(AuthenticationRequest authenticationRequest);


}
