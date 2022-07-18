package carsharing.menu;

import carsharing.DatabaseManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private CompanyMenu companyMenu = new CompanyMenu();

    public void runMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        String msg = "1. Log in as a manager\n" +
                "0. Exit";

        while (!exit) {
            System.out.println(msg);
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    companyMenu.runMenu();
                    break;
                case 0:
                    exit = true;
                    DatabaseManager.closeConnection();
                    break;
            }
        }
    }
}
