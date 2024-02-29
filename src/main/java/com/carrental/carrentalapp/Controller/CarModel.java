package com.carrental.carrentalapp.Controller;

public enum CarModel {
    // Toyota
    COROLLA(CarMake.TOYOTA),
    CAMRY(CarMake.TOYOTA),
    RAV4(CarMake.TOYOTA),
    // Honda
    ACCORD(CarMake.HONDA),
    CIVIC(CarMake.HONDA),
    CRV(CarMake.HONDA),
    // Ford
    MUSTANG(CarMake.FORD),
    FOCUS(CarMake.FORD),
    ESCAPE(CarMake.FORD),
    // BMW
    SERIES_3(CarMake.BMW),
    SERIES_5(CarMake.BMW),
    X5(CarMake.BMW),
    // Mercedes
    C_CLASS(CarMake.MERCEDES),
    E_CLASS(CarMake.MERCEDES),
    S_CLASS(CarMake.MERCEDES),
    // Audi
    A3(CarMake.AUDI),
    A4(CarMake.AUDI),
    Q5(CarMake.AUDI);
    private CarMake carMake;
    CarModel(CarMake carMake) {
        this.carMake = carMake;
    }
    public CarMake getCarMake() {
        return carMake;
    }
}