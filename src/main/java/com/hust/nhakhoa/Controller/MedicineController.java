package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Request.MedicineRequest;
import com.hust.nhakhoa.Service.MedicineService;
import com.hust.nhakhoa.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/medicine/all")
    ResponseEntity<?> getAllMedicine() {
        ResponseEntity<?> resp;
        try {
            List<Medicine> medicineList = medicineService.getAllMedicine();
            if (medicineList != null) {
                resp = new ResponseEntity<>(medicineList, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("Not found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @GetMapping("/medicine/one/{id}")
    ResponseEntity<?> getMedicineById(@PathVariable ("id") Integer medicineID) {
        ResponseEntity<?> resp;
        try {
            Medicine medicine = medicineService.getMedicineById(medicineID);
            if (medicine != null) {
                resp = new ResponseEntity<Medicine>(medicine, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("No Data Found", (HttpStatus.NO_CONTENT));
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/medicine/add")
    ResponseEntity<?> addMedicine(@Valid @RequestBody MedicineRequest medicineRequest){
        ResponseEntity<?> resp;
        Medicine medicine = medicineService.addMedicine(medicineRequest);

        try{
            resp =  new ResponseEntity<String>("MedicineId registered with "+ medicine.getId(),HttpStatus.CREATED);
        } catch (Exception e){
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @PostMapping("/medicine/update/{id}")
    public ResponseEntity<?> updateMedicine(@PathVariable("id") Integer medicineID,@Valid @RequestBody MedicineRequest medicineRequest){

        ResponseEntity<?> resp;
        Medicine existingMedicine = medicineService.getMedicineById(medicineID);
        existingMedicine.setId(medicineID);
        existingMedicine.setName(medicineRequest.getName());
        existingMedicine.setMedicineCode(medicineRequest.getMedicineCode());
        existingMedicine.setPrice(medicineRequest.getPrice());
        medicineService.updateMedicine(medicineID,medicineRequest);
        try {
            resp =  new ResponseEntity<String>("Medicine registered with "+ medicineRequest.getId(),HttpStatus.CREATED);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'EMPLOYEE')")
    @DeleteMapping("/medicine/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Integer medicineId){
        ResponseEntity<?> resp=null;
        try {
            medicineService.deleteProductById(medicineId);
            resp = new ResponseEntity<String>("Medicine "+medicineId +" deleted",HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }


}
