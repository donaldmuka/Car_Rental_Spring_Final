package com.carrental.carrentalapp.Model;
import com.carrental.carrentalapp.Controller.CarMake;
import com.carrental.carrentalapp.Controller.CarModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARS")
public class Car {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "car_id")
        private Long id;
        @ManyToOne
        @JoinColumn(name = "car_branch", nullable = false)
        @JsonBackReference
        private Branch branch;
        @Column(name = "model")
        private CarModel model;
        @Column(name = "brand")
        private CarMake brand;
        @Column(name = "year_of_manufacture")
        private Integer yearOfManufacture;
        @Column(name = "car_color")
        private String color;
        @Column(name = "car_milage")
        private Double mileage;
        @Column(name = "amount_per_day")
        private Double amountPerDay = 20.0;
        @OneToMany(mappedBy = "status")
        private Set<Status> statuses;
        @Column(name = "image")
        private String image;
        @OneToMany(mappedBy = "car")
        @JsonIgnore
        private Set<Reservation> reservations;
    }