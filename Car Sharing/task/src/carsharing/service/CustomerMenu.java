package carsharing.service;

import carsharing.dao.*;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;

import java.util.List;

public class CustomerMenu {
    private static CustomerMenu customerMenu;

    private CustomerDao customerDao = new CustomerDaoImpl();
    private CarDao carDao = new CarDaoImpl();
    private CompanyDao companyDao = new CompanyDaoImpl();

    public void runCustomerMainMenu() {
        List<Customer> customerList = customerDao.getAllCustomers();

        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!");
            return;
        }

        String menuMsg = createCustomerMainMenuMessage(customerList);
        Menu menu = new Menu(menuMsg, 0, customerList.size());


        while (!menu.isStopped()) {
            int choice = menu.printMenuAndGetUserChoice();

            if (choice == 0) {
                menu.stop();
            } else {
                // the index of customer in printed menu starts at 1,
                // but the index in the list starts at 0
                Customer customer = customerList.get(choice - 1);
                runCustomerMenuForCustomer(customer);
            }
        }
    }

    private String createCustomerMainMenuMessage(List<Customer> customerList) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < customerList.size(); i++) {
            sb.append(String.format("%d. %s\n", (i+1), customerList.get(i).getName()));
        }

        sb.append("0. Back");
        return sb.toString();
    }

    private void runCustomerMenuForCustomer(Customer customer) {
        String menuMsg = "\n1. Rent a car\n" +
                "2. Return a rented car\n" +
                "3. My rented car\n" +
                "0. Back";

        Menu menu = new Menu(menuMsg, 0, 4);

        while (!menu.isStopped()) {
            int choice = menu.printMenuAndGetUserChoice();

            if (choice == 0) {
                menu.stop();
            } else {
                executeMenuCommand(choice, customer);
            }
        }
    }

    private void executeMenuCommand(int commandNum, Customer customer) {
        switch (commandNum) {
            case 1:
                customerRentCar(customer);
                break;
            case 2:
                customerReturnRentedCar(customer);
                break;
            case 3:
                printCustomerRentedCarInfo(customer);
                break;

        }
    }

    private void customerRentCar(Customer customer) {
        if (customer.hasAlreadyRentedACar()) {
            System.out.println("\nYou've already rented a car!");
        } else {
            Car car = chooseACarOfACompany();
            if (car != null) {
                customer.rentCar(car.getId());
                customerDao.rentCar(customer.getId(), car.getId());
                System.out.println(String.format("\nYou rented '%s'", car.getName()));
            }
        }
    }

    private Car chooseACarOfACompany() {
        Company company = CompanyMenu.getInstance().runChooseACompanyMenu();

        // company == null when use chooses the option to go back in the menu
        if (company != null) {

            Car car = CarMenu.getInstance().runChooseACarOfCompany(company);

            return car;
        }

        return null;
    }

    private void printCustomerRentedCarInfo(Customer customer) {
        if (customer.hasAlreadyRentedACar()) {
            Car car = carDao.getCarById(customer.getRentedCarId());
            Company company = companyDao.getCompanyById(car.getCompanyId());

            System.out.println("\nYour rented car: ");
            System.out.println(car.getName());
            System.out.println("Company: ");
            System.out.println(company.getName());
        } else {
            System.out.println("\nYou didn't rent a car!");
        }
    }

    private void customerReturnRentedCar(Customer customer) {
        if (customer.hasAlreadyRentedACar()) {
            customer.returnRentedCar();
            customerDao.returnRentedCar(customer.getId());
            System.out.println("\nYou've returned a rented car!");
        } else {
            System.out.println("\nYou didn't rent a car!");
        }
    }

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }
}
