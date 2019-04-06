import java.sql.Connection;

import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import Customer.Customer;
import Customer.CustomerFacade;
import DB.Database;

public class Test {

	public static void main(String[] args) throws Exception {
		Database.checkConection();
		Database.dropAllTables();
		Database.createAllTables();
		//Database.createAllTables();
		//Database.dropAllTables();
		//Database.geUrl();
		//System.out.println("##########");
		//Database.geUrl();
		//Database.createTableCompany();
		//System.out.println("##########");
		Company c1 = new Company(8, "Dell", "abc123", "admin@dell.com");
		Company company = new Company(6, "Dell", "abc123", "admin@dell.com");
		Company c2 = new Company(9, "Dell", "abc123", "emc@dell.com");
		CompanyFacade facade = new CompanyFacade();
		//System.out.println("##########");
		facade.createCompany(c1);
		facade.createCompany(c2);
		facade.createCompany(company);
		//System.out.println("#### deleting ######");
		//facade.delCompany(c2);
		
		//facade.delCompany(c2);
		//facade.delCompany(company);
		//Company company2 = new Company(8, "ddd", "aa", "aa");
		//facade.createCompany(company);
		//facade.updateCompany(company2, "abc", "abc", "abc");
		//facade.delCompany(1);
		//Database.createTableCoupon();
		System.out.println("#### get ######");
		//facade.getCompany(4);
		facade.getAllCompanies();
		CustomerFacade customerFacade = new CustomerFacade();
		Customer cust1 = new Customer(1, "Shlomi", "abc123");
		Customer customer = new Customer(6, "Shlomi", "abc123");
		customerFacade.createCustomer(cust1);
		customerFacade.createCustomer(customer);
		System.out.println("#### get customer ######");
		customerFacade.getAllCustomers();

	}
}