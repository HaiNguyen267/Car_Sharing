package carsharing;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    List<Company> getAllCompanies() throws SQLException;
    void createACompany(String companyName) throws SQLException;
}
