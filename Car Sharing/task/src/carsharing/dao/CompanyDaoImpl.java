package carsharing.dao;

import carsharing.DatabaseManager;
import carsharing.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{
    @Override
    public List<Company> getAllCompanies() {
        String sql = "SELECT * FROM company";
        List<Company> list = new ArrayList<>();

        try (Statement stmt
                     = DatabaseManager.getConnection().createStatement()
        ) {
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Company company = new Company(resultSet.getInt("id"),
                                              resultSet.getString("name")
                );
                list.add(company);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void createCompany(String companyName) {
        String sql = "INSERT INTO company (name)" +
                "VALUES (?)";

        try (PreparedStatement preparedStmt
                     = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setString(1, companyName);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company getCompanyById(int companyId) {
        String sql = "SELECT * FROM company " +
                "WHERE id = (?)";

        Company company = null;
        try (PreparedStatement preparedStmt
                = DatabaseManager.getConnection().prepareStatement(sql)
        ) {
            preparedStmt.setInt(1, companyId);
            ResultSet resultSet = preparedStmt.executeQuery();

            // there will be only 1 result
            while (resultSet.next()) {
                company = new Company(resultSet.getInt("id"),
                        resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }
}
