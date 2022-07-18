package carsharing.company;

import carsharing.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{
    @Override
    public List<Company> getAllCompanies() throws SQLException {
        String sql = "SELECT * FROM company";
        List<Company> list = new ArrayList<Company>();

        try (Statement stmt = DatabaseManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                list.add(new Company(resultSet.getLong("id"), resultSet.getString("name")));
            }
        }

        return list;
    }

    @Override
    public void createCompany(String companyName) throws SQLException {
        String sql = "INSERT INTO company (name) VALUES (?)";

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setString(1, companyName);
            preparedStmt.executeUpdate();
        }
    }
}
