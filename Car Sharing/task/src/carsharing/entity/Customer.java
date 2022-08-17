package carsharing.entity;

public class Customer {

    private int id;
    private String name;
    private int rentedCarId;

    public Customer(int id, String name, int rentedCarId) {
        this.id = id;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRentedCarId() {
        return rentedCarId;
    }

    public void rentCar(int carId) {
        this.rentedCarId = carId;
    }

    public void returnRentedCar() {
        this.rentedCarId = 0;
    }
    public boolean hasAlreadyRentedACar() {
        return rentedCarId != 0;
    }
}
