package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Department;
import com.carrental.carrentalapp.Service.DepartamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartamentService departamentService;
    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }
    @GetMapping("")
    public List<Department> getAllDepartaments() {
        return departamentService.getAllDepartament();
    }
    @GetMapping("/{id}")
    public Department getDepartamentById(@PathVariable Long id) {
        return departamentService.getDepartamentById(id);
    }
    @PostMapping("")
    public Department createDepartment(@RequestBody Department department, @RequestParam Long branch_id){
        return departamentService.saveDepartament(department,branch_id);
    }
    @PostMapping("/all")
    public List<Department> saveAllDepartament(@RequestBody List<Department> department) {
        return departamentService.saveAllDepartament(department);
    }
    @DeleteMapping("/{departament_id}")
    public void deleteDepartamentById(@PathVariable Long departament_id){
        departamentService.deleteDepartament(departament_id);
    }
}

