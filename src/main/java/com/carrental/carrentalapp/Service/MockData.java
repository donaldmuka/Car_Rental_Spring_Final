package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Controller.CarMake;
import com.carrental.carrentalapp.Controller.CarModel;
import com.carrental.carrentalapp.Model.*;
import com.carrental.carrentalapp.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

// Use this Service to generate Mock data for the application

@Component
public class MockData {

//    Generate random values
    static Random rand = new Random();

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DepartamentRepository departamentRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @PostConstruct
    public void initData() {
        try {
            Role role =roleRepository.findById(1L).orElseThrow(()->new RuntimeException("user not found"));
        }catch(Exception e){
            roleRepository.save(new Role(0L,"ROLE_ADMIN"));
            roleRepository.save(new Role(0L,"ROLE_USER"));
        }
        try{
            AppUser myAppUser = userService.getUserById(1L);
        }catch(Exception e){
            AppUser appUser2 = new AppUser(0L, "Olger","Olger","Muci", encoder.encode("Olger"), "Olger@gmail.com", Set.of(roleRepository.findById(1L).orElse(roleService.findByRoleName("ROLE_ADMIN"))));
            AppUser appUser1 = new AppUser(0L, "Enri","Enri","Skenderaj", encoder.encode("Enri"), "Enri@gmail.com", Set.of(roleService.findByRoleName("ROLE_USER")));

            userService.saveUser(appUser2);
            userService.saveUser(appUser1);
        }
        try {
            Address address = addressRepository.findById(1L).orElseThrow(()->new RuntimeException("address not found for id"));
        }catch(Exception e){
            createDummyAddresses();
        }

        try {
            rentalRepository.findById(1L).orElseThrow(()->new RuntimeException("rental not fount for id"));
        }catch (Exception e){
            createDummyRentals();
        }
        try {
            branchRepository.findById(1L).orElseThrow(()->new RuntimeException("branch not fount for id"));
        }catch (Exception e){
            createDummyBranches();
        }
        try {
            carRepository.findById(1L).orElseThrow(()->new RuntimeException("car not fount for id"));
        }catch (Exception e){
            createDummyCars();
        }
        try {
            departamentRepository.findById(1L).orElseThrow(()->new RuntimeException("department not fount for id"));
        }catch (Exception e){
            createDummyDepartments();
        }
        try {
           reservationRepository.findById(1L).orElseThrow(()->new RuntimeException("reservation not fount for id"));
        }catch (Exception e){
            createDummyReservations();
        }
        }
        private void createDummyAddresses () {
            for (int i = 1; i <= 10; i++) {
                Address address = new Address();
                address.setApartment("Apt " + i);
                address.setStreet("Street " + i);
                address.setCity("City " + i);
                address.setState("State " + i);
                addressRepository.save(address);
            }
        }
        private void createDummyRentals () {
            for (int i = 1; i <= 2; i++) {
                Rental rental = new Rental();
                rental.setName("Rental Company " + i);
                rental.setInternetDomain("domain" + i);
                rental.setLogo("logo" + i + ".png");
                rental.setRevenue(1000000L * i);
                rental.setAddress("Test");
                rentalRepository.save(rental);
            }
        }
        public void createDummyBranches () {
            for (int i = 1; i <= 10; i++) {
                Branch branch = new Branch();
                branch.setName("Branch " + i);
                branch.setAddress("Test");
                branch.setRental(rentalRepository.findById(rand.nextLong(1, 2)).orElse(null));
                branchRepository.save(branch);
            }
        }
        private void createDummyCars () {
            List<Branch> branches = branchRepository.findAll();
            CarModel[] carModels = CarModel.values();
            CarMake[] carMakes = CarMake.values();
            for (int i = 1; i <= branches.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    Car car = new Car();
                    car.setBranch(branchRepository.findById(0L + i).orElse(null));
                    car.setModel(getRandomCarModel(carModels));
                    car.setYearOfManufacture(getRandomNumber(2000, 2022));
                    car.setBrand(getRandomCarBrands(carMakes));
                    car.setColor(getRandomColor());
                    car.setMileage(getRandomNumber(0, 100000) * 0.1);
                    car.setAmountPerDay(getRandomNumber(200,500)*0.1);
                    car.setImage("assets/images/IMG_15"+rand.nextInt(0,1)+rand.nextInt(1,9)+".jpg");
                    carRepository.save(car);
                }
            }
        }
        private CarModel getRandomCarModel (CarModel[]carModels){
            Random random = new Random();
            return carModels[random.nextInt(carModels.length)];
        }
        private CarMake getRandomCarBrands (CarMake[]carMakes){
            Random random = new Random();
            return carMakes[random.nextInt(carMakes.length)];
    }
        private static int getRandomNumber ( int min, int max){
            return new Random().nextInt(max - min + 1) + min;
        }

        private static Long getRandomLong ( int min, int max){
            return new Random().nextLong(max - min + 1) + min;
        }

        private static String getRandomColor () {
            String[] colors = {"Red", "Blue", "Green", "Yellow", "Black", "White", "Silver"};
            return colors[new Random().nextInt(colors.length)];
        }
        private void createDummyDepartments () {
            for (int i = 1; i <= branchRepository.findAll().size(); i++) {
                Department department = new Department();
                department.setName("Department " + i);
                department.setBranch(branchRepository.findById((long) i).orElse(null));
                departamentRepository.save(department);
            }
        }
        private void createDummyReservations () {
            for (int i = 1; i <= 10; i++) {
                Reservation reservation = new Reservation();
                reservation.setDateOfBooking(LocalDateTime.now());
                Branch branch = branchRepository.findById(rand.nextLong(1, branchRepository.count())).orElse(null);
                reservation.setBranchOfLoan(branch);
                reservation.setCar(branch.getCars().stream().findAny().get());
                reservation.setDateTo(LocalDateTime.now().plusDays(i));
                reservation.setAmount(100 * rand.nextLong(1, 100));
                reservationRepository.save(reservation);
            }
        }
    }