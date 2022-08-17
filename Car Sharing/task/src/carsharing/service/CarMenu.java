package carsharing.service;

import carsharing.dao.CarDao;
import carsharing.dao.CarDaoImpl;
import carsharing.entity.Car;
import carsharing.entity.Company;

import java.util.List;
import java.util.Scanner;

public class CarMenu {
    private static CarMenu carMenu;

    private CarDao carDao = new CarDaoImpl();

    public void runCarMenuForCompany(Company company) {

        String companyName = company.getName();
        int companyId = company.getId();

        String menuMsg = String.format("\n'%s' company\n" +
                "1. Car list\n" +
                "2. Create a car\n" +
                "0. Back", companyName);


        Menu menu = new Menu(menuMsg, 0, 2);

        while (!menu.isStopped()) {
            int choice = menu.printMenuAndGetUserChoice();

            if (choice == 0) {
                menu.stop();
            } else {
                executeMenuCommand(choice, companyId);
            }
        }
    }

    private void executeMenuCommand(int choice, int companyId) {
        switch (choice) {
            case 1:
                printAllCarsOfCompany(companyId);
                break;
            case 2:
                createCar(companyId);
                break;
        }
    }

    private void printAllCarsOfCompany(int companyId) {
        List<Car> carList = carDao.getAllCarsOfCompany(companyId);

        if (carList.isEmpty()) {
            System.out.println("\nThe car list is empty!");
            return;
        }

        String msg = createCarListMessage(carList);
        System.out.println(msg);
    }

    private String createCarListMessage(List<Car> carList) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < carList.size(); i++) {
            sb.append(String.format("%d. %s\n", (i + 1), carList.get(i).getName()));
        }
        sb.append("0. Back");
        return sb.toString();
    }

    private void createCar(int companyId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the car name: ");
        String carName = sc.nextLine();

        carDao.createCar(carName, companyId);
        System.out.println("The car was added!");
    }

    public Car runChooseACarOfCompany(Company company) {
        List<Car> carList = carDao.getAllCarsOfCompany(company.getId());

        if (carList.isEmpty()) {
            System.out.println(String.format("\nNo available cars in the '%s'", company.getName()));
            return null;
        }

        String msg = createCarListMessage(carList);
        Menu menu = new Menu(msg, 0, carList.size());

        System.out.println("\nChoose a car:");
        int choice = menu.printMenuAndGetUserChoice();
        if (choice == 0) {
            menu.stop();
        } else {
            return carList.get(choice - 1);
        }

        return null;
    }

    public static CarMenu getInstance() {
        if (carMenu == null) {
            carMenu = new CarMenu();
        }

        return carMenu;
    }

}

