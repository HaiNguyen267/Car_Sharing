package carsharing.service;

import carsharing.dao.CompanyDao;
import carsharing.dao.CompanyDaoImpl;
import carsharing.entity.Company;

import java.util.List;
import java.util.Scanner;

public class CompanyMenu {
    private static CompanyMenu companyMenu;
    private CompanyDao companyDao = new CompanyDaoImpl();

    public void runCompanyMenu() {
        String menuMsg = "\n1. Company list\n" +
                "2. Create a company \n" +
                "0. Back";

        Menu menu = new Menu(menuMsg, 0, 2);

        while (!menu.isStopped()) {
            int choice = menu.printMenuAndGetUserChoice();

            if (choice == 0) {
                menu.stop();
            } else {
                performCommand(choice);
            }
        }
    }

    private void performCommand(int commandNum) {
        switch (commandNum) {
            case 1:
                runCompanyListMenu();
                break;
            case 2:
                createCompany();
                break;
        }
    }

    private void runCompanyListMenu() {
        List<Company> companyList = companyDao.getAllCompanies();

        if (companyList.isEmpty()) {
            System.out.println("\nThe company list is empty!");
            return;
        }

        String menuMsg = createCompanyListMenuMessage(companyList);
        Menu menu = new Menu(menuMsg, 0, companyList.size());


        System.out.println("\nChoose a company:");
        int choice = menu.printMenuAndGetUserChoice();

        if (choice == 0) {
            menu.stop();
        } else {
            // The index of company in printed menu starts at 0,
            // but the index of the list starts at 0
            Company company = companyList.get(choice - 1);
            CarMenu.getInstance().runCarMenuForCompany(company);
        }

    }

    private String createCompanyListMenuMessage(List<Company> companyList) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < companyList.size(); i++) {
            sb.append(String.format("%d. %s\n", (i+1), companyList.get(i).getName()));
        }

        sb.append("0. Back");
        return sb.toString();
    }

    private void createCompany() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the company name: ");
        String companyName = sc.nextLine();

        companyDao.createCompany(companyName);
        System.out.println("The company was created!");
    }

    public Company runChooseACompanyMenu() {
        List<Company> companyList = companyDao.getAllCompanies();

        if (companyList.isEmpty()) {
            System.out.println("\nThe company list is empty!");
            return null;
        }

        String menuMsg = createCompanyListMenuMessage(companyList);
        Menu menu = new Menu(menuMsg, 0, companyList.size());


        System.out.println("\nChoose a company:");
        int choice = menu.printMenuAndGetUserChoice();

        if (choice == 0) {
            menu.stop();
        } else {
            // The index of company in printed menu starts at 0,
            // but the index of the list starts at 0
            Company company = companyList.get(choice - 1);
            return company;

        }

        return null;
    }
    public static CompanyMenu getInstance() {
        if (companyMenu == null) {
            companyMenu = new CompanyMenu();
        }

        return companyMenu;
    }


}
