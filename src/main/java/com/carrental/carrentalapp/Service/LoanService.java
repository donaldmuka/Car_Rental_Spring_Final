package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Employee;
import com.carrental.carrentalapp.Model.Loan;
import com.carrental.carrentalapp.Repository.EmployeeRepository;
import com.carrental.carrentalapp.Repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//Modify this service if you want to implement Loans to the app
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final EmployeeRepository employeeRepository;

    public LoanService(LoanRepository loanRepository, EmployeeRepository employeeRepository) {
        this.loanRepository = loanRepository;
        this.employeeRepository = employeeRepository;
    }
    public List<Loan> getAllLoan(){
        return loanRepository.findAll();
    }
    public Loan getLoanById(Long id){
        return loanRepository.findById(id).orElseThrow(()->new RuntimeException("Loan not found"));
    }
    public Loan saveLoan (Loan loan, Long employee_id){
        Employee employee= employeeRepository.findById(employee_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan must have an employee" + employee_id));
        loan.setEmployee(employee);
        return loanRepository.save(loan);
    }
    public void deleteLoan(Long id){
        loanRepository.deleteById(id);
    }
}

