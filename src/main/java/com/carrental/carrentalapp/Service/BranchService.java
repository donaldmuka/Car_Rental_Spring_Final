package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Address;
import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Model.Rental;
import com.carrental.carrentalapp.Repository.AddressRepository;
import com.carrental.carrentalapp.Repository.BranchRepository;
import com.carrental.carrentalapp.Repository.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//Modify this service if you want to implement Branches to the app


@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final RentalRepository rentalRepository;
    private final AddressRepository addressRepository;

    public BranchService(BranchRepository branchRepository, RentalRepository rentalRepository, AddressRepository addressRepository) {
        this.branchRepository = branchRepository;
        this.rentalRepository = rentalRepository;
        this.addressRepository = addressRepository;
    }

    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Long id) {
        return branchRepository.findById(id).orElseThrow(()->new RuntimeException("Branch not found with id "+id));

    }

    public Branch saveBranch(Branch branch, Long rental_id) {
        Rental rental = rentalRepository.findById(rental_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found with id: " + rental_id));
        branch.setRental(rental);
        return branchRepository.save(branch);
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }

    public List<Branch> saveAllBranch(List<Branch> branchs) {
        return branchs;
    }

    public void saveAllBranches(List<Branch> branches) {
        branchRepository.saveAll(branches);
    }
}

