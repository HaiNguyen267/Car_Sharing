package carsharing.dao;

import carsharing.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();

    void createCustomer(String customerName);

    void rentCar(int customerId, int carId);

    void returnRentedCar(int customerId);
}
