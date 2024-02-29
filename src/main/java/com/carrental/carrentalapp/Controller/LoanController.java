package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Loan;
import com.carrental.carrentalapp.Service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping("/loan")
    public Loan addLoan(@RequestBody Loan loan, @RequestParam Long employee_id) { return loanService.saveLoan(loan,employee_id);
    }
    @GetMapping("/loan/{loan_id}")
    public Loan getLoanById(@PathVariable Long loan_id){
        return loanService.getLoanById(loan_id);
    }
    @GetMapping("/loan")
    public List<Loan> getLoan() {
        return loanService.getAllLoan();
    }
    @DeleteMapping("loan/{loan_id}")
    public void deleteLoanById(@PathVariable Long loan_id){
        loanService.deleteLoan(loan_id);
    }
}