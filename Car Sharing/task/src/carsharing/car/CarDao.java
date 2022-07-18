package carsharing.car;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {
    List<Car> getAllCars(long companyId) throws SQLException;

    void createCar(String carName, long companyId) throws SQLException;
}
