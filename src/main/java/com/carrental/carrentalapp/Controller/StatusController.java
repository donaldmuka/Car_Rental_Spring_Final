package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Status;
import com.carrental.carrentalapp.Service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }
    @GetMapping
    public List<Status> getAllStatus() {
        return statusService.getAllStatus();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long id) {
        Status status = statusService.getStatusById(id);
        return ResponseEntity.ok(status);
    }
    @PostMapping("")
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        Status createdStatus = statusService.saveStatus(status);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }
    @DeleteMapping("/{status_id}")
    public void deleteStatusById(@PathVariable Long status_id){
        statusService.deleteStatus(status_id);
    }
}
