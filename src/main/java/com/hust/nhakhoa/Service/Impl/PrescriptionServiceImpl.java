package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.DTO.AppointmentDTO;
import com.hust.nhakhoa.DTO.PrescriptionDTO;
import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Appointment;
import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Model.Patient;
import com.hust.nhakhoa.Model.Prescription;
import com.hust.nhakhoa.Repository.MedicineRepository;
import com.hust.nhakhoa.Repository.PatientRepository;
import com.hust.nhakhoa.Repository.PrescriptionRepository;
import com.hust.nhakhoa.Request.PrescriptionRequest;
import com.hust.nhakhoa.Request.ServiceRequet;
import com.hust.nhakhoa.Service.MedicineService;
import com.hust.nhakhoa.Service.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptonRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private ModelMapper modelMapper;

    private Prescription getPrescriptionOrThrow(Integer id) {
        return prescriptonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "prescription id", id));
    }

    private Medicine getMedicineOrThrow(Integer id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "prescription id", id));
    }



    @Override
    public List<Prescription> getAllPrescription() {
        return prescriptonRepository.findAll();
    }

    @Override
    public PrescriptionDTO getPrescriptionById(Integer prescriptionId) {
        Prescription prescription = getPrescriptionOrThrow(prescriptionId);
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }

    @Override
    public PrescriptionDTO addPrescription(PrescriptionRequest prescriptionRequest) {
        Prescription prescription = new Prescription();
        prescription.setFinalPrice(countTotalPrice(prescriptionRequest));
        saveDataToPrescription(prescription,prescriptionRequest);
        prescriptonRepository.save(prescription);
        return modelMapper.map(prescription,PrescriptionDTO.class);

    }

    @Override
    public PrescriptionDTO addMedicineByPrescription(Integer prescriptionId, Integer medicineId) {
        Prescription prescription = getPrescriptionOrThrow(prescriptionId);
        Medicine medicine = getMedicineOrThrow(medicineId);
        prescription.getMedicineList().add(medicine);
        medicine.getList().add(prescription);
        prescription.setFinalPrice(medicineService.calculateTotalPrice(prescription.getMedicineList()));
        prescriptonRepository.save(prescription);
        return modelMapper.map(prescription,PrescriptionDTO.class);
    }


    @Override
    public PrescriptionDTO updatePrescription(Integer prescriptionId, PrescriptionRequest prescriptionRequest) {
        Prescription prescription = getPrescriptionOrThrow(prescriptionId);
        prescription.setFinalPrice(countTotalPrice(prescriptionRequest));
        saveDataToPrescription(prescription,prescriptionRequest);
        prescriptonRepository.save(prescription);
        return modelMapper.map(prescription,PrescriptionDTO.class);
    }

    @Override
    public Double countTotalPrice(PrescriptionRequest prescriptionRequest) {

        List<Medicine> medicineList = new ArrayList<>();

        for(Integer id : prescriptionRequest.getMedicineId()) {
            Medicine medicine = medicineRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine", "medicine id", id));
            medicineList.add(medicine);
        }
        return medicineList.stream()
                .mapToDouble(Medicine::getPrice).sum();
    }

    @Override
    public PrescriptionDTO deletePrescription(Integer prescriptionId) {
        Prescription prescription = getPrescriptionOrThrow(prescriptionId);
        prescriptonRepository.delete(prescription);
        return modelMapper.map(prescription,PrescriptionDTO.class);
    }

    private void saveDataToPrescription(Prescription prescription, PrescriptionRequest prescriptionRequest) {

        List<Medicine> medicineList = new ArrayList<>();

        for(Integer id : prescriptionRequest.getMedicineId()) {
            Medicine medicine = medicineRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Medicine", "medicine id", id));
            medicineList.add(medicine);
        }

        Patient patient = patientRepository.findById(prescriptionRequest.getPatientId())
                .orElseThrow(() ->new ResourceNotFoundException("Patient", "patient id", prescriptionRequest.getPatientId()));

        prescription.setPatient(patient);
        prescription.setName(prescriptionRequest.getName());
        prescription.setNote(prescriptionRequest.getNote());
        prescription.setMedicineList(medicineList);
        prescription.setFinalPrice(countTotalPrice(prescriptionRequest));



    }
}
