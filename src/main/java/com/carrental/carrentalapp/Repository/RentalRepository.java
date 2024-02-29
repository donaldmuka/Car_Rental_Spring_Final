package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.Rental;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {
}
