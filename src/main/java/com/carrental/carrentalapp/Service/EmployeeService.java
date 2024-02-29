package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Model.Employee;
import com.carrental.carrentalapp.Repository.BranchRepository;
import com.carrental.carrentalapp.Repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//Modify this service if you want to add an employee management to the app
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    public EmployeeService(EmployeeRepository employeeRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found with id " + id));
    }
    public Employee saveEmployee(Employee employee,Long branch_id) {
        Branch branch= branchRepository.findById(branch_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee must be part of a branch" + branch_id));
        employee.setBranch(branch);
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
