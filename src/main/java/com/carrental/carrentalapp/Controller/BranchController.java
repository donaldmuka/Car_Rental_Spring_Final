package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Service.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
    @GetMapping("")
    public List<Branch> getAllBranch() {
        return branchService.getAllBranch();
    }
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id);
    }
    @PostMapping("")
    public Branch createBranch(@RequestBody Branch branch, @RequestParam Long rental_id){
        return branchService.saveBranch(branch,rental_id);
    }
    @PostMapping("/all")
    public List<Branch> saveAllBranch(@RequestBody List<Branch> branchs) {
        return branchService.saveAllBranch(branchs);
    }
    @DeleteMapping("/{branch_id}")
    public void deleteBranchById(@PathVariable Long branch_id){
        branchService.deleteBranch(branch_id);
    }
}
