package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.Address;
import com.carrental.carrentalapp.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByState(String state);

}
