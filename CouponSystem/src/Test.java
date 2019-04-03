import java.sql.Connection;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;


public class Test {

	public static void main(String[] args) throws Exception {
		Database.checkConection();
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
		//facade.addCompany(c1);
		//facade.addCompany(c2);
		System.out.println("#### deleting ######");
		//facade.delCompany(c2);
		
		//facade.delCompany(c2);
		//facade.delCompany(company);
		//Company company2 = new Company(8, "ddd", "aa", "aa");
		//facade.addCompany(company2);
		//facade.updateCompany(company2, "abc", "abc", "abc");
		//facade.delCompany(1);
		//Database.createTableCoupon();
		System.out.println("#### get ######");
		facade.getCompany(3);

	}
}