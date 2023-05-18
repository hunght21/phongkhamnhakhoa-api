package com.hust.nhakhoa.Controller;

import com.hust.nhakhoa.Model.Gender;
import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Request.MedicineRequest;
import com.hust.nhakhoa.Request.PatientRequest;
import com.hust.nhakhoa.Service.MedicineService;
import com.hust.nhakhoa.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PrescriptionService prescriptionService;

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


//    @PostMapping("/{prescriptionId}/medicine")
//    public ResponseEntity<Medicine> addMedicineByPrescription(@PathVariable("prescriptionId") Integer prescriptionID, @RequestBody MedicineRequest medicineRequest) {
//        Medicine medicine = prescriptionService.getPrescriptionById(prescriptionID).map(tutorial -> {
//            long tagId = tagRequest.getId();
//
//            // tag is existed
//            if (tagId != 0L) {
//                Tag _tag = tagRepository.findById(tagId)
//                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
//                tutorial.addTag(_tag);
//                tutorialRepository.save(tutorial);
//                return _tag;
//            }
//
//            // add and create new Tag
//            tutorial.addTag(tagRequest);
//            return tagRepository.save(tagRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
//
//        return new ResponseEntity<>(tag, HttpStatus.CREATED);
//    }

}
