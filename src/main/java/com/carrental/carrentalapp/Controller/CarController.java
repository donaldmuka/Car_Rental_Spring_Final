package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping("/car")
    public Car addCar(@RequestBody Car car,@RequestParam Long branch_id) { return carService.saveCar(car,branch_id);
    }
    @PostMapping("/createcar")
    public Car createCar(@RequestBody Car car) { return carService.createCar(car);
    }
    @GetMapping("/car/{car_id}")
    public Car getCarById(@PathVariable Long car_id){
        return carService.getCarById(car_id);
    }
    @GetMapping("/car")
    public List<Car> getCar() {
        return carService.getAllCar();
    }
    @GetMapping("/car/models")
    public CarModel[] getCarModels() {
         return CarModel.values();
    }
    @DeleteMapping("car/{car_id}")
    public void deleteCarById(@PathVariable Long car_id){
       carService.deleteCar(car_id);
    }
    @GetMapping("car/year/{year}")
    public List<Car> getCarsYear(@PathVariable Integer year){
        return  carService.filterByYear(year);
    }
    @GetMapping("car/search")
    public List<Car> getFilteredCars(@RequestParam Optional<List<String>> models, Optional<Integer> year, Optional<Double> budget){
        return  carService.filterCars(models,budget,year);
    }
}