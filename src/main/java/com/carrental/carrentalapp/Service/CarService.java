package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Branch;
import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Repository.BranchRepository;
import com.carrental.carrentalapp.Repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
// This is the Service to handle all the car requests anf filtering

@Service
public class CarService {
    private final CarRepository carRepository;
    private final BranchRepository branchRepository;

    public CarService(CarRepository carRepository, BranchRepository branchRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
    }

    public List<Car> getAllCar(){
        return carRepository.findAll();
    }
    public Car getCarById(Long id){
        return carRepository.findById(id).orElseThrow(()->new RuntimeException("Car not found with id "+id));

    }
    public Car saveCar(Car car, Long branch_id) {
       Branch branch = branchRepository.findById(branch_id).orElseThrow(
               ()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found with id: " + branch_id));
       car.setBranch(branch);
       return carRepository.save(car);
    }
    public void deleteCar(Long id){
         carRepository.deleteById(id);
    }
//    Filtering for the cars implemented in the search result page in the front
    public List<Car> filterByYear(Integer year) {
        return carRepository.findByYearOfManufactureAfter(year);
    }
    public List<Car> filterCars(Optional<List<String>> models, Optional<Double> budget, Optional<Integer> year) {
        return carRepository.findByAmountPerDayLessThanEqualAndYearOfManufactureAfterAndModelIn(budget,year,models);
    }
//    Use this if you will implement branches and not pass a static 1L in the methode
    public Car createCar(Car car) {
        car.setBranch(branchRepository.findById(1L).orElseThrow(()->new RuntimeException("Branch not found")));
        return this.carRepository.save(car);
    }
}
