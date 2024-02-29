package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Address;
import com.carrental.carrentalapp.Repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// The final app does not need this service because it is connected with the Google Maps API
//But you can use this service or pass the address as a simple String if you need it

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }
    public Address getAddressById(Long id){
        return addressRepository.findById(id).orElseThrow(()->new RuntimeException("Address not found"));
    }
    public Address saveAddress (Address address){
        return addressRepository.save(address);
    }
    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }
}