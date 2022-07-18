package carsharing.menu;

import carsharing.company.Company;
import carsharing.company.CompanyDao;
import carsharing.company.CompanyDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CompanyMenu {
    private CompanyDao companyDao = new CompanyDaoImpl();
    private CarMenu carMenu = new CarMenu();

    public void runMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);

        boolean back = false;
        String msg = "\n" +
                "1. Company list\n" +
                "2. Create a company\n" +
                "0. Back";

        while (!back) {
            System.out.println(msg);
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    companyList();
                    break;
                case 2:
                    createCompany();
                    break;
                case 0:
                    back = true;
                    break;
            }
        }
    }

    private void companyList() throws SQLException {
        List<Company> list = companyDao.getAllCompanies();

        if (list.isEmpty()) {
            System.out.println("\nThe company list is empty!");
        } else {
            chooseCompanyMenu(list);
        }
    }

    private void chooseCompanyMenu(List<Company> list) throws SQLException {
        Scanner sc = new Scanner(System.in);


        printCompanyList(list);

        int choice = Integer.parseInt(sc.nextLine());

        if (choice != 0) {
            // the index of printed company list starts from 1, but the index of list starts from 0
            Company chosenCompany = list.get(choice - 1);
            carMenu.runMenuForCompany(chosenCompany);
        }


    }

    private void printCompanyList(List<Company> list) {

        System.out.println("\nChoose the company:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s", (i+1), list.get(i).getName()));
        }
        System.out.println("0. Back");
    }

    private void createCompany() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the company name:");
        String companyName = sc.nextLine();

        companyDao.createCompany(companyName);
        System.out.println("The company was created!");
    }
}
