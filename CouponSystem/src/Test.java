import java.sql.Connection;

import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import DB.Database;

public class Test {

	public static void main(String[] args) throws Exception {
		Database.checkConection();
		//Database.createAllTables();
		Database.dropAllTables();
		//DB.getUrl();
		//System.out.println("##########");
		//Database.geUrl();
		//Database.createTableCompany();
		//System.out.println("##########");
		//Company c1 = new Company(1, "Dell", "abc123", "admin@dell.com");
		//Company c2 = new Company(1, "Dell", "abc123", "emc@dell.com");
		//CompanyFacade facade = new CompanyFacade();
		//System.out.println("##########");
		//facade.addCompany(c1);
		
		//Database.createTableCoupon();

	}
}