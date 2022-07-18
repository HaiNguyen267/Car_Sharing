package carsharing.menu;

import carsharing.car.Car;
import carsharing.car.CarDao;
import carsharing.car.CarDaoImpl;
import carsharing.company.Company;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CarMenu {
    private CarDao carDao = new CarDaoImpl();

    public void runMenuForCompany(Company company) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String companyName = company.getName();
        long companyId = company.getId();

        boolean back = false;
        String msg =  "\n1. Car list\n" +
                "2. Create a car\n" +
                "0. Back";


        System.out.println(String.format("\n'%s' company", companyName));
        while (!back) {
            System.out.println(msg);

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    carList(companyId);
                    break;
                case 2:
                    createCar(companyId);
                    break;
                case 0:
                    back = true;
                    break;
            }
        }
    }

    private void carList(long companyId) throws SQLException {
        List<Car> list = carDao.getAllCars(companyId);

        if (list.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            printCarList(list);
        }
    }

    private void printCarList(List<Car> list) {
        System.out.println("\nCar list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s", (i+1), list.get(i).getName()));
        }
    }

    private void createCar(long companyId) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the car name:");
        String carName = sc.nextLine();

        carDao.createCar(carName, companyId);
        System.out.println("The car was added!");
    }
}
