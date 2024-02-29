package com.carrental.carrentalapp.Model;

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
@Table(name = "STATUSES")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long id;
    @Column(name = "status")
    private Statuses status;
    @Column(name = "status_date")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

}
