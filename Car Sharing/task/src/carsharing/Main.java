package carsharing;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
//
//    private static Connection connection;
//
//    public static void main(String[] args) {
//        try {
//            createTableCompany(args);
//            runMenu();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void createTableCompany(String[] args) throws ClassNotFoundException, SQLException {
//        String dbName = parseDBName(args);
//        String jdbc_driver = "org.h2.Driver";
//        String db_url = "jdbc:h2:./src/carsharing/db/" + dbName;
//
//
//        Class.forName(jdbc_driver);
//
//        connection = DriverManager.getConnection(db_url);
//
//        String sql = "CREATE TABLE IF NOT EXISTS company (" +
//                "id INT PRIMARY KEY AUTO_INCREMENT," +
//                "name VARCHAR(255) UNIQUE NOT NULL" +
//                ");";
//
//        try (Statement stmt = connection.createStatement()) {
//            stmt.executeUpdate(sql);
//        }
//    }
//
//    private static String parseDBName(String[] args) {
//        List<String> list = Arrays.asList(args);
//
//        if (list.contains("-databaseFileName")) {
//            int index = list.indexOf("-databaseFileName");
//            return list.get(index + 1);
//        } else {
//            return "mydatabase";
//        }
//    }
//
//
//    private static void runMenu() throws SQLException {
//        Scanner sc = new Scanner(System.in);
//
//        boolean exit = false;
//        String msg = "1. Log in as a manager\n" +
//                "0. Exit";
//
//        while (!exit) {
//            System.out.println(msg);
//            int choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1:
//                    runMenuForManager();
//                    break;
//                case 0:
//                    exit = true;
//                    break;
//            }
//
//        }
//    }
//
//    private static void runMenuForManager() throws SQLException {
//        Scanner sc = new Scanner(System.in);
//
//        boolean back = false;
//        String msg = "\n1. Company list\n" +
//                "2. Create a company\n" +
//                "0. Back";
//
//        while (!back) {
//            System.out.println(msg);
//            int choice = Integer.parseInt(sc.nextLine());
//
//            switch (choice) {
//                case 1: printCompanyList(); break;
//                case 2: createACompany(); break;
//                case 0: back = true; break;
//            }
//
//        }
//    }
//
//
//    private static void printCompanyList() throws SQLException {
//        String sql = "SELECT * FROM company";
//
//        try (Statement stmt = connection.createStatement()) {
//            ResultSet resultSet = stmt.executeQuery(sql);
//
//            // if there are any returned companies
//            if (resultSet.isBeforeFirst()) {
//                int count = 1;
//
//                System.out.println("\nCompany list:");
//                while (resultSet.next()) {
//                    System.out.println(String.format("%d. %s", count, resultSet.getString("name")));
//                    count++;
//                }
//            } else {
//                System.out.println("\nThe company list is empty!");
//            }
//
//        }
//    }
//
//
//    private static void createACompany() throws SQLException {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("\nEnter the company name:");
//        String companyName = sc.nextLine();
//
//        String sql = "INSERT INTO company (name) VALUES (?)";
//
//        try (PreparedStatement preparedStmt = connection.prepareStatement(sql)) {
//            preparedStmt.setString(1, companyName);
//            preparedStmt.executeUpdate();
//
//            System.out.println("The company was created!");
//
//        }
//    }

    public static void main(String[] args) {
        try {
            Menu menu = new Menu(args);
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}