package com.carrental.carrentalapp.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Use this controller to combine all the data requests and not have them in separated controllers
@RestController
@RequestMapping("/carrental/v1/data/")
@AllArgsConstructor
public class MainDataController {

}
