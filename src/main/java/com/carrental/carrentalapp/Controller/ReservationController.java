package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Reservation;
import com.carrental.carrentalapp.Service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/carrental/v1")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
    @GetMapping("/reservation")
    public List<Reservation> getAllReservations(Principal principal) {
        return reservationService.getAllUserRes(principal);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }
    @PostMapping("")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return reservationService.saveReservation(reservation);
    }
    @PostMapping("/all")
    public List<Reservation> saveAllReservation(@RequestBody List<Reservation> reservation) {
        return reservationService.saveAllReservation(reservation);
    }
    @DeleteMapping("/{reservation_id}")
    public void deleteReservationById(@PathVariable Long reservation_id) {
        reservationService.deleteReservation(reservation_id);
    }
}
