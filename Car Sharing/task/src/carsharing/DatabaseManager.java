package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static Connection connection;

    public static void initDatabase(String dbName) throws SQLException, ClassNotFoundException {
        initConnection(dbName);
        initTables();
    }

    private static void initConnection(String dbName) throws SQLException, ClassNotFoundException {
        String jdbc_driver = "org.h2.Driver";
        String db_url = "jdbc:h2:C:\\Users\\NKcomputer\\IdeaProjects\\Car Sharing\\Car Sharing\\task\\src\\carsharing\\db\\" + dbName;

        Class.forName(jdbc_driver);
        connection = DriverManager.getConnection(db_url);
    }

    private static void initTables() throws SQLException {
        String createCompanyTableQuery =
                "CREATE TABLE IF NOT EXISTS company (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "name VARCHAR NOT NULL UNIQUE" +
                        ");";

        String createCarTableQuery =
                "CREATE TABLE IF NOT EXISTS car (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT," +
                        "name VARCHAR NOT NULL UNIQUE," +
                        "company_id INT NOT NULL," +
                        "FOREIGN KEY (company_id) REFERENCES company(id)"+
                        ");";


        try (Statement stmt = DatabaseManager.getConnection().createStatement()) {
            stmt.executeUpdate(createCompanyTableQuery);
            stmt.executeUpdate(createCarTableQuery);
        }
    }
    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
