package com.carrental.carrentalapp;

import com.carrental.carrentalapp.Controller.CarModel;
import com.carrental.carrentalapp.Model.*;
import com.carrental.carrentalapp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.*;
import java.util.random.RandomGenerator;

@SpringBootApplication
public class CarrentalappApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarrentalappApplication.class, args);
	}
}


