package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Status;
import com.carrental.carrentalapp.Repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//Simple status service if you need it
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(Long statusId) {
    }
}
