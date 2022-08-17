package carsharing.dao;

import carsharing.entity.Car;

import java.util.List;

public interface CarDao {

    List<Car> getAllCarsOfCompany(int companyId);

    Car getCarById(int carId);
    void createCar(String carName, int companyId);


}
