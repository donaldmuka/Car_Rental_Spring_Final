package com.carrental.carrentalapp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RENTALS")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;
    @Column(name = "rental_name",nullable = false)
    private String name;
    @Column(name = "rental_internet_domain")
    private String internetDomain = "domain";
    @Column(name = "rental_logo")
    private String logo;
    @Column(name = "rental_revenue")
    private Long revenue;
    @ManyToOne
    @JoinColumn(name="owner")
    private AppUser owner;
    @Column(name = "rental_address")
    private String address;
}