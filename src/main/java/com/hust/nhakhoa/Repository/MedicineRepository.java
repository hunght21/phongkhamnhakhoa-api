package com.hust.nhakhoa.Repository;

import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
 //   List<Medicine> findMedicineByPrescriptionId(Integer prescriptionId);

}
