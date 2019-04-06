package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Company.Company;

public class CustomerDBDAO implements CustomerDAO{

	@Override
	public void createCustomer(Customer customer) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "INSERT INTO CUSTOMER (ID, CUST_NAME, PASSWORD) VALUES (?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setLong(1, customer.getId());
			pstmt.setString(2, customer.getCust_name());
			pstmt.setString(3, customer.getPassword());
			

			pstmt.executeUpdate();
			System.out.println("Insert " + customer + " succeed ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Customer creation failed");

		} finally {
			connection.close();
		}
		
	}

	@Override
	public void removeCustomer(Customer customer) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "DELETE FROM CUSTOMER WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, customer.getId());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Deleted " + customer + " succesfully ");
		} catch (SQLException e) {
			System.out.println("Connection to table Customer has failed");

		} finally {
			connection.close();
		}
		
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "UPDATE COMPANY SET CUST_NAME=?,PASSWORD=? WHERE ID=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, customer.getCust_name());
			pstmt.setString(2, customer.getPassword());
			pstmt.setLong(3, customer.getId());

			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Updated " + customer + " succesfully ");
		} catch (SQLException e) {
			System.out.println("Connection to table customer has failed");

		} finally {
			connection.close();
		}
		
	}

	@Override
	public Customer getCustomer(long id) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";
		Customer customer = new Customer();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				
				String cust = resultSet.getString("CUST_NAME");
				String pass = resultSet.getString("PASSWORD");
				customer = new Customer(id, cust, pass);
				System.out.println(customer);
			}
			pstmt.close();
			
			
		} catch (SQLException e) {
			System.out.println("can't get connection to customer table");

		} finally {
			connection.close();
		}
		return customer;
	}

	@Override
	public Set<Customer> getAllCustomers() throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		Set<Customer> set = new HashSet<>();
		String sql = "SELECT * FROM CUSTOMER";
		try (Statement stm = connection.createStatement(); ResultSet resultSet = stm.executeQuery(sql)) {
			Customer customer = new Customer();
			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				String cust = resultSet.getString("CUST_NAME");
				String pass = resultSet.getString("PASSWORD");
				

				customer = new Customer(id, cust, pass);
				System.out.println(customer);
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new Exception("Unable To Retrieve Customers Data");
		} finally {
			connection.close();
		}
		return set;
	}

	}
	

