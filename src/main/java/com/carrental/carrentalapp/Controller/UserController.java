package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.AppUser;
import com.carrental.carrentalapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/carrental/v1/")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("profile")
    public ResponseEntity<?> getProfile(Principal principal){
        return ResponseEntity.ok().body(principal);
    }
    @PostMapping("/register")
    public AppUser createUser(@RequestBody AppUser appUser) {
        return userService.saveNewUser(appUser);
    }
    @PostMapping("/edituser")
    public AppUser editUser(@RequestBody AppUser appUser) {
        return userService.updateUser(appUser);
    }
}
