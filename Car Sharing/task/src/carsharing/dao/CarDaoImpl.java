package carsharing.dao;

import carsharing.DatabaseManager;
import carsharing.entity.Car;
import carsharing.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    @Override
    public List<Car> getAllCarsOfCompany(int companyId) {
        String sql = "SELECT * FROM car " +
                "WHERE company_id = (?)";
        List<Car> list = new ArrayList<>();

        try (PreparedStatement preparedStmt =
                DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setInt(1, companyId);

            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id")
                );

                list.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Car getCarById(int carId) {
        String sql = "SELECT * FROM car " +
                "WHERE id = (?)";

        Car car = null;
        try (PreparedStatement preparedStmt =
                     DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setInt(1, carId);
            ResultSet resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                // there will be only one result
                car = new Car(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  car;
    }

    @Override
    public void createCar(String carName, int companyId) {
        String sql = "INSERT INTO car (name, company_Id)" +
                "VALUES (?, ?)";

        try (PreparedStatement preparedStmt =
                DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setString(1, carName);
            preparedStmt.setInt(2, companyId);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
