package com.hust.nhakhoa.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "medicine")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "medicine_ma")
    @NotNull
    private String medicineCode;

    @Column(name = "medicine_name")
    private String name;


    @ManyToMany(mappedBy = "medicineList")
    @JsonIgnore
    private List<Prescription> list;

//    @Column(columnDefinition = "DECIMAL(8, 2)")
//    @DecimalMin(value = "0.0", inclusive = false)
//    @Digits(integer = 6, fraction = 2)
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
