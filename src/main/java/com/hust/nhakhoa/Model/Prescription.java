package com.hust.nhakhoa.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "prescription")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prescription_name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "prescription_price")
    private Double finalPrice;

    @Column(name = "prescription_note")
    private String note;

    public Prescription(String name, Double finalPrice, String note) {
        this.name = name;
        this.finalPrice = finalPrice;
        this.note = note;
    }

    @ManyToOne
 //   @JsonBackReference
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany
    @JoinTable(
            name = "prescription_medicine",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
//    @NotEmpty(message = "List of services must have at least one service")
    private List<Medicine> medicineList;

//    public void addMedicine(Medicine medicine) {
//        this.medicineList.add(medicine);
//        medicine.getList().add(this);
//    }
//
//    public void removeMedicine(int medicineId) {
//        Medicine medicine = this.medicineList.stream().filter(t -> t.getId() == medicineId).findFirst().orElse(null);
//        if (medicine != null) {
//            this.medicineList.remove(medicineList);
//            medicine.getList().remove(this);
//        }
//    }

}
