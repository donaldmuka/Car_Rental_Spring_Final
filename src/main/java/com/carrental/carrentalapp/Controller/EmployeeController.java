package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Model.Employee;
import com.carrental.carrentalapp.Repository.EmployeeRepository;
import com.carrental.carrentalapp.Service.CarService;
import com.carrental.carrentalapp.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee,@RequestParam Long branch_id) { return employeeService.saveEmployee(employee,branch_id);
    }
    @GetMapping("/employee/{employee_id}")
    public Employee getEmployeeById(@PathVariable Long employee_id){
        return employeeService.getEmployeeById(employee_id);
    }
    @GetMapping("/employee")
    public List<Employee> getEmployee() {
        return employeeService.getAllEmployee();
    }
    @DeleteMapping("employee/{employee_id}")
    public void deleteEmployeeById(@PathVariable Long employee_id){
        employeeService.deleteEmployee(employee_id);
    }
}