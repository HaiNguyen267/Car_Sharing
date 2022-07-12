package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{

    private Connection connection;

    public CompanyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        String sql = "SELECT * FROM company";
        List<Company> list = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new Company(resultSet.getInt("id"), resultSet.getString("name")));
            }
        }

        return list;
    }

    @Override
    public void createACompany(String companyName) throws SQLException {
        String sql = "INSERT INTO company (name) VALUES (?)";

        try (PreparedStatement preparedStmt = connection.prepareStatement(sql)) {
            preparedStmt.setString(1, companyName);
            preparedStmt.executeUpdate();
        }
    }
}
