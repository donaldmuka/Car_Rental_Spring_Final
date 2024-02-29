package com.carrental.carrentalapp.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "LOANS")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "loan_employee")
    private Employee employee;
    @Column(name = "date_of_rental")
    private LocalDate dateOfRental;
    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;
    @Column(name = "loan_comment")
    private String comment;
}