package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}