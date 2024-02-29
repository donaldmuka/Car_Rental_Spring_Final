package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Rental;
import com.carrental.carrentalapp.Repository.RentalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// User this service if you want to add rentals to the app because at the moment they are not used in the frontend
@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
    public List<Rental> getAllRental(){
        return rentalRepository.findAll();
        }
        public Rental getRentalById(Long id){
        return rentalRepository.findById(id).orElseThrow(()->new RuntimeException("Rental not found with id "+id));
    }
    public Rental saveRental (Rental rental){
        return rentalRepository.save(rental);
    }
    public void deleteRental(Long id){
        rentalRepository.deleteById(id);
    }
    public List<Rental> saveAllRental(List<Rental> rentals) {
        return rentals;
    }
}
