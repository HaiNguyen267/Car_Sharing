package carsharing.dao;

import carsharing.DatabaseManager;
import carsharing.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";

        List<Customer> list = new ArrayList<Customer>();

        try (Statement stmt =
                     DatabaseManager.getConnection().createStatement()
        ) {
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("rented_car_id")
                );
                list.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void createCustomer(String customerName) {
        String sql = "INSERT INTO customer (name, rented_car_id) VALUES (?, NULL)";

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setString(1, customerName);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rentCar(int customerId, int carId) {
        String sql = "UPDATE customer " +
                "SET rented_car_id = (?)" +
                "WHERE id = (?)";

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setInt(1, carId);
            preparedStmt.setInt(2, customerId);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void returnRentedCar(int customerId) {
        String sql = "UPDATE customer " +
                "SET rented_car_id = NULL " +
                "WHERE id = (?)";

        try (PreparedStatement preparedStmt
                = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setInt(1, customerId);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
