package Company;


import java.sql.SQLException;
import java.util.Set;

public class CompanyFacade {
	private CompanyDBDAO prodDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company C) {
		this.company = C;
	}

	public CompanyFacade() {
	}

	public void createCompany(Company company) throws Exception {
		prodDAO.createCompany(company);
		
	}

	public void removeCompany(Company company) throws Exception {
		prodDAO.removeCompany(company);
	}

	public void updateCompany(Company company, String newCompName, String newPassword, String newEmail) throws Exception {
		company.setCompName(newCompName);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		prodDAO.updateCompany(company);
	}

	public Company getCompany(long id) throws SQLException {
				return prodDAO.getCompany(id);
	}

	public Set<Company> getAllCompanies() throws Exception {
		// ProductDBDAO comDAO=new ProductDBDAO();
		return prodDAO.getAllCompanies();
	}

}