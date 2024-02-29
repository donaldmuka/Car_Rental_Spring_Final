package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Model.Department;
import com.carrental.carrentalapp.Repository.BranchRepository;
import com.carrental.carrentalapp.Repository.DepartamentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Modify this service if you want to implement Departments to the app


@Service
public class DepartamentService {

    private final DepartamentRepository departamentRepository;
    private final BranchRepository branchRepository;

    public DepartamentService(DepartamentRepository departamentRepository, BranchRepository branchRepository) {
        this.departamentRepository = departamentRepository;
        this.branchRepository = branchRepository;
    }
    public List<Department> getAllDepartament() {
        return departamentRepository.findAll();
    }

    public Department getDepartamentById(Long id) {
        return departamentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found with id " +id));
    }
    public Department saveDepartament(Department department, Long branch_id) {
        Branch branch = branchRepository.findById(branch_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found with id: " + branch_id));
        department.setBranch(branch);
        return departamentRepository.save(department);
    }
    public void deleteDepartament(Long departament_id){
        departamentRepository.deleteById(departament_id);
    }
    public List<Department> saveAllDepartament(List<Department> departments) {
        return departamentRepository.saveAll(departments);
    }
}
