package com.hust.nhakhoa.Repository;

import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    boolean existsByEmail(String email);
    Optional<Patient> findByEmail(String email);
}
