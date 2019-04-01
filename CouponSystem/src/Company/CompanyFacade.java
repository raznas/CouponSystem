package Company;

import java.util.Set;

public class CompanyFacade {
	private CompanyDBDAO prodDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company C) {
		this.company = C;
	}

	public CompanyFacade() {
	}

	public void addCompany(Company company) throws Exception {
		prodDAO.addCompany(company);
		
	}

	public void delCompany(Company company) throws Exception {
		prodDAO.delCompany(company);
	}

	public void updateCompany(Company company, String newCompName, String newPassword, String newEmail) throws Exception {
		company.setCompName(newCompName);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		prodDAO.updateCompany(company);
	}

	public Company getCompany() {
		return company;
	}

	public Set<Company> getAllCompanies() throws Exception {
		// ProductDBDAO comDAO=new ProductDBDAO();
		return prodDAO.getAllCompanies();
	}

}