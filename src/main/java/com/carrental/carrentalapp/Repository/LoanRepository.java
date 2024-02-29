package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
