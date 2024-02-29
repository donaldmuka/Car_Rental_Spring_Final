package com.carrental.carrentalapp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_of_booking")
    private LocalDateTime dateOfBooking;
    @ManyToOne
    @JoinColumn(name = "reservation_car")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "costumer")
    @JsonBackReference
    private AppUser client;
    @Column(name = "date_to")
    private LocalDateTime dateTo;
    @ManyToOne
    @JoinColumn(name = "branch_of_loan")
    @JsonBackReference
    private Branch branchOfLoan;
    @Column(name = "amount")
    private Long amount;

}
