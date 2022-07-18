package carsharing.company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies() throws SQLException;

    void createCompany(String companyName) throws SQLException;
}
