package com.hust.nhakhoa.Service.Impl;

import com.hust.nhakhoa.Exceptions.ResourceNotFoundException;
import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Repository.MedicineRepository;
import com.hust.nhakhoa.Request.MedicineRequest;
import com.hust.nhakhoa.Service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private ModelMapper modelMapper;


    private Medicine getMedicineOrThrow(Integer id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "prescription id", id));
    }
    @Override
    public List<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine addMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = modelMapper.map(medicineRequest,Medicine.class);
        return medicineRepository.save(medicine);
    }


    @Override
    public Medicine getMedicineById(Integer medicineId) {
        return medicineRepository.findById(medicineId).get();
    }

    @Override
    public Double calculateTotalPrice(List<Medicine> medicineList) {
        return medicineList.stream()
                .mapToDouble(Medicine::getPrice)
                .sum();
    }

    @Override
    public Medicine updateMedicine(Integer medicineId,MedicineRequest medicineRequest) {
         Medicine medicine = getMedicineOrThrow(medicineId);
         modelMapper.map(medicineRequest,medicine);
        return medicineRepository.save(medicine);
    }

    @Override
    public void deleteProductById(Integer medicineId) {
        Medicine medicine = getMedicineOrThrow(medicineId);
        medicineRepository.delete(medicine);
    }
}
