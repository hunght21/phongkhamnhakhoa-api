package com.hust.nhakhoa.Repository;

import com.hust.nhakhoa.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsByEmail(String email);
}
