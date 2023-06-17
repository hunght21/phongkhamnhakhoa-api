package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.Request.AuthenticationRequest;
import com.hust.nhakhoa.Request.RegisterPatientRequest;
import com.hust.nhakhoa.Request.RegisterRequest;
import com.hust.nhakhoa.Response.JwtAuthResponse;
import com.hust.nhakhoa.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/registerpatient")
    public ResponseEntity<JwtAuthResponse> registerPatient(
            @Valid @RequestBody RegisterPatientRequest registerPatientRequest) {
        return ResponseEntity.ok(authenticationService.registerPatient(registerPatientRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }


}
