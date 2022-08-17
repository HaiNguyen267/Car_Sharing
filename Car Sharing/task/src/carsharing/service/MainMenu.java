package carsharing.service;

import carsharing.dao.CustomerDao;
import carsharing.dao.CustomerDaoImpl;

import java.util.Scanner;

public class MainMenu {
    private static MainMenu mainMenu;

    private CustomerDao customerDao = new CustomerDaoImpl();
    public void runMainMenu() {
        String menuMsg = "\n1. Log in as a manager\n" +
                "2. Log in as a customer\n" +
                "3. Create a customer\n" +
                "0. Exit";
        Menu menu = new Menu(menuMsg, 0, 4);
        while (!menu.isStopped()) {
            int choice = menu.printMenuAndGetUserChoice();

            if (choice == 0) {
                menu.stop();
            } else {
                executeCommand(choice);
            }
        }
    }

    private void executeCommand(int commandNum) {
        switch (commandNum) {
            case 1:
                CompanyMenu.getInstance().runCompanyMenu();
                break;
            case 2:
                CustomerMenu.getInstance().runCustomerMainMenu();
                break;
            case 3:
                createCustomer();
                break;
        }
    }

    private void createCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the customer name: ");
        String customerName = sc.nextLine();

        customerDao.createCustomer(customerName);
        System.out.println("The customer was added!");
    }

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }

        return mainMenu;
    }
}
