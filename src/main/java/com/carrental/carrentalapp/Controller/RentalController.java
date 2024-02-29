package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Rental;
import com.carrental.carrentalapp.Service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("rental")
public class RentalController {
    private final RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @PostMapping("")
    public Rental createRental(@RequestBody Rental rental){
        return rentalService.saveRental(rental);
    }
    @PostMapping("/all_rental")
    public List<Rental> saveAllRental(@RequestBody List<Rental> rentals) {
        return rentalService.saveAllRental(rentals);
    }
    @GetMapping("")
    public List<Rental> getAllRental() {
        return rentalService.getAllRental();
    }
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }
    @DeleteMapping("/{rental_id}")
    public void deleteRentalById(@PathVariable Long rental_id){
        rentalService.deleteRental(rental_id);
    }
}

