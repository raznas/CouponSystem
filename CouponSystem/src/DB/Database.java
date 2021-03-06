package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.ToDoubleBiFunction;

import javax.xml.bind.DataBindingException;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Database {

	 static final String url = "jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT";
	 static final String user = "root";
	 static final String password = "ofirVD1995";

	public static void checkConection() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		if (connection != null) {
			System.out.println("Connected to the database Coupon_DB");
		}
		}
		public static String getUrl() throws SQLException {
		return url; 
			
			}
		public static String getUser() throws SQLException {
		return user; 
			
			}
		public static String getPass() throws SQLException {
		return password; 
			
			}
			
	public static void createTableCompany() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS COMPANY (ID INT NOT NULL PRIMARY KEY ,COMP_NAME VARCHAR(25) NOT NULL, PASSWORD VARCHAR(25) NOT NULL, EMAIL VARCHAR(50) NOT NULL)");
		System.out.println("COMPANY table has been created");
	}
	public static void createTableCustomer() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS CUSTOMER (ID INT NOT NULL PRIMARY KEY ,CUST_NAME VARCHAR(25) NOT NULL, PASSWORD VARCHAR(50) NOT NULL)");
		System.out.println("CUSTOMER table has been created");
	}

	public static void createTableCoupon() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS COUPON (ID INT NOT NULL PRIMARY KEY ,TITLE VARCHAR(25) NOT NULL, START_DATE DATETIME NOT NULL, END_DATE DATETIME NOT NULL ,AMOUNT INT NOT NULL,  MESSAGE VARCHAR(50) NOT NULL, PRICE INT NOT NULL, IMAGE VARCHAR(25) NOT NULL)");
		System.out.println("COUPON table has been created");
	}
	public static void createTableCustomer_Coupon() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS CUSTOMER_COUPON (CUST_ID INT NOT NULL, COUPON_ID INT NOT NULL, FOREIGN KEY(CUST_ID) REFERENCES CUSTOMER(ID), FOREIGN KEY(COUPON_ID) REFERENCES COUPON(ID), PRIMARY KEY (CUST_ID, COUPON_ID))");
		System.out.println("Customer_Coupon table has been created");
	}
	public static void createTableCompany_Coupon() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE IF NOT EXISTS COMPANY_COUPON (COMP_ID INT NOT NULL, COUPON_ID INT NOT NULL, FOREIGN KEY(COMP_ID) REFERENCES COMPANY(ID), FOREIGN KEY(COUPON_ID) REFERENCES COUPON(ID), PRIMARY KEY (COMP_ID, COUPON_ID))");
		System.out.println("Company_Coupon table has been created");
	}
	
	public static void createAllTables() throws SQLException {
		createTableCompany();
		createTableCustomer();
		createTableCoupon();
		createTableCustomer_Coupon();
		createTableCompany_Coupon();
		System.out.println("All tables have been created");
	}
	
	public static void dropAllTables() throws SQLException {
		Connection connection = DriverManager.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS COMPANY, CUSTOMER, COUPON, COMPANY_COUPON,CUSTOMER_COUPON ");
		System.out.println("All tables have been dropped");
	}
}
