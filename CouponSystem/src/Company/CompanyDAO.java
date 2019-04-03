package Company;

import java.util.Set;

public interface CompanyDAO {
	void createCompany (Company company) throws Exception;
	void removeCompany (Company company) throws Exception;
	void updateCompany (Company company) throws Exception;
	Company getCompany (long id) throws Exception;
	Set<Company> getAllCompanies() throws Exception;
	
}
