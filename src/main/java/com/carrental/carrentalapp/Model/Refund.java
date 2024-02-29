package com.carrental.carrentalapp.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "REFUNDS")
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "refund_employee")
    private Employee employee;
    @Column(name = "date_of_refund")
    private LocalDate dateOfReturn;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    @Column(name = "subcharge")
    private Double surcharge;
    @Column(name = "refund_comment")
    private String comment;

}
