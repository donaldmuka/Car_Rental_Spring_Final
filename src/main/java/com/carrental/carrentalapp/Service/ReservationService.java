package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.AppUser;
import com.carrental.carrentalapp.Model.Reservation;
import com.carrental.carrentalapp.Repository.ReservationRepository;
import com.carrental.carrentalapp.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    public ReservationService(ReservationRepository reservationRepository,UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    public List<Reservation> getAllUserRes(Principal principal) {
        AppUser user = userRepository.findByUsername(principal.getName());
        return reservationRepository.findByClient_Id(user.getId());
    }
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    public void deleteReservation(Long reservationId) {
    }
    public List<Reservation> saveAllReservation(List<Reservation> reservation) {
        return reservation;
    }
}
