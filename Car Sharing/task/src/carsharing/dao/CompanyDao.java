package carsharing.dao;

import carsharing.entity.Company;

import java.util.List;

public interface CompanyDao {


    List<Company> getAllCompanies();

    void createCompany(String companyName);

    Company getCompanyById(int companyId);
}
