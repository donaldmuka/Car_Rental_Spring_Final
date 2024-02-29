package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
