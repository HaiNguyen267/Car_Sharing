package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Connection connection;
    private CompanyDaoImpl companyDaoImpl;

    public Menu(String[] args) throws SQLException, ClassNotFoundException {
        String dbName = parseDBName(args);
        this.connection = initConnection(dbName);
        this.companyDaoImpl = new CompanyDaoImpl(connection);
        createTableCompany();
    }

    private String parseDBName(String[] arguments) {
        List<String> list = Arrays.asList(arguments);

        if (list.contains("-databaseFileName")) {
            int index = list.indexOf("-databaseFileName");
            return list.get(index + 1);
        } else {
            return "mydatabase";
        }
    }
    private Connection initConnection(String dbName) throws SQLException, ClassNotFoundException {
        String jdbc_driver = "org.h2.Driver";
        String db_url = "jdbc:h2:./src/carsharing/db/" + dbName;

        Class.forName(jdbc_driver);
        return DriverManager.getConnection(db_url);
    }

    private void createTableCompany() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS company (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR UNIQUE NOT NULL " +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    public void run() throws SQLException {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        String msg = "1. Log in as a manager\n" +
                "0. Exit";

        while (!exit) {
            System.out.println(msg);

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    runManagerMenu();
                    break;
                case 0:
                    connection.close();
                    exit = true;
            }
        }
    }

    private void runManagerMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);

        boolean back = false;
        String msg = "\n1. Company list\n" +
                "2. Create a company\n" +
                "0. Back";

        while (!back) {
            System.out.println(msg);

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    printCompanyList();
                    break;
                case 2:
                    createACompany();
                    break;
                case 0:
                    back = true;
                    break;
            }
        }
    }

    private void printCompanyList() throws SQLException {
        List<Company> list = companyDaoImpl.getAllCompanies();

        if (list.isEmpty()) {
            System.out.println("\nThe company list is empty!");
        } else {
            System.out.println("\nCompany list:");
            for (Company company : list) {
                System.out.println(String.format("%d. %s", company.getId(), company.getName()));
            }
        }
    }

    private void createACompany() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the company name:");
        String companyName = sc.nextLine();

        companyDaoImpl.createACompany(companyName);
        System.out.println("The company was created!");
    }
}
