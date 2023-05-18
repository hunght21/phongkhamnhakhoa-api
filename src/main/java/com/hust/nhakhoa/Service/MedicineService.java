package com.hust.nhakhoa.Service;

import com.hust.nhakhoa.Model.Medicine;
import com.hust.nhakhoa.Request.MedicineRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineService {

    List<Medicine> getAllMedicine();

    Medicine addMedicine(MedicineRequest medicineRequest);

    Medicine getMedicineById(Integer medicineId);
    public Double calculateTotalPrice(List<Medicine> medicineList);

    Medicine updateMedicine (Integer medicineId, MedicineRequest medicineRequest);

    void deleteProductById(Integer medicineId);

}
