package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Controller.CarModel;
import com.carrental.carrentalapp.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByYearOfManufactureAfter(Integer year);
    List<Car> findByAmountPerDayLessThanEqualAndYearOfManufactureAfter(Optional<Double> budget, Optional<Integer> year);

    default List<Car> filterCars(Optional<List<String>> models, Optional<Double> budget, Optional<Integer> year) {
        return null;
    }
    List<Car> findByAmountPerDayLessThanEqualAndYearOfManufactureAfterAndModelIn(Optional<Double> budget, Optional<Integer> year,Optional<List<String>>models);


}

