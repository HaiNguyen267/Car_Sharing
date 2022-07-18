package carsharing.car;

import carsharing.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {


    @Override
    public List<Car> getAllCars(long companyId) throws SQLException {
        String sql = "SELECT * FROM car WHERE company_id = (?)";
        List<Car> list = new ArrayList<Car>();

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setLong(1, companyId);

            ResultSet resultSet = preparedStmt.executeQuery();

            while (resultSet.next()) {
                list.add(new Car(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }

        return list;
    }

    @Override
    public void createCar(String carName, long companyId) throws SQLException {
        String sql = "INSERT INTO car (name, company_id) VALUES (?, ?)";

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setString(1, carName);
            preparedStmt.setLong(2, companyId);

            preparedStmt.executeUpdate();
        }
    }
}
