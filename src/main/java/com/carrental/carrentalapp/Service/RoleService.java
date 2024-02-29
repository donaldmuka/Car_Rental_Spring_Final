package com.carrental.carrentalapp.Service;
import com.carrental.carrentalapp.Model.Role;
import com.carrental.carrentalapp.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This is the Role Service for the Spring Security
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
    public Role findByRoleName(String name){
        return roleRepository.findByRole(name).orElseThrow(() -> new RuntimeException("Role with name " + name + " not found "));
    }
}

