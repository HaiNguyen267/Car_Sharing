package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static Connection connection;

    public static void initDatabase(String dbName) {
        try {
            initConnection(dbName);
            initTables();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initConnection(String dbName) throws ClassNotFoundException, SQLException {
        String jdbcDriver = "org.h2.Driver";
        String dbUrl = "jdbc:h2:C:\\Users\\NKcomputer\\IdeaProjects\\Car Sharing\\Car Sharing\\task\\src\\carsharing\\db\\" + dbName;

        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(dbUrl);
    }

    private static void initTables() throws SQLException {
        String tableCompanySql = "CREATE TABLE IF NOT EXISTS company (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR UNIQUE NOT NULL" +
                ");";

        String tableCarSql = "CREATE TABLE IF NOT EXISTS car (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR UNIQUE NOT NULL," +
                "company_id INT NOT NULL," +
                "FOREIGN KEY(company_id) REFERENCES company(id)" +
                ");";
        String tableCustomerSql = "CREATE TABLE IF NOT EXISTS customer(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR UNIQUE NOT NULL," +
                "rented_car_id INT," +
                "FOREIGN KEY(rented_car_id) REFERENCES car(id)" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(tableCompanySql);
            stmt.executeUpdate(tableCarSql);
            stmt.executeUpdate(tableCustomerSql);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
