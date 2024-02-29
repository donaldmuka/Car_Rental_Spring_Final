package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Address;
import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Service.AddressService;
import com.carrental.carrentalapp.Service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping("")
    public Address createAddress(@RequestBody Address address)
    { return addressService.saveAddress(address);}
    @GetMapping("/{address_id}")
    public Address getAddressById(@PathVariable Long address_id){
        return addressService.getAddressById(address_id);
    }
    @GetMapping("")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }
    @DeleteMapping("/{address_id}")
    public void deleteAddressById(@PathVariable Long address_id){
        addressService.deleteAddress(address_id);
    }
}