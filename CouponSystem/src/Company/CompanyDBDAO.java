package Company;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.jdbc.DatabaseMetaData;

import DB.Database;

public class CompanyDBDAO implements CompanyDAO {
	Connection connection;

	@Override
	public void createCompany(Company company) throws Exception {
		//Connection connection = DriverManager.getConnection(
			//	"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				//"root", "root");
		
		Connection connection = DriverManager.getConnection(Database.getUrl(), Database.getUser(), Database.getPass());
		String sql = "INSERT INTO COMPANY (ID, COMP_NAME, PASSWORD, EMAIL) VALUES (?,?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setLong(1, company.getId());
			pstmt.setString(2, company.getCompName());
			pstmt.setString(3, company.getPassword());
			pstmt.setString(4, company.getEmail());

			pstmt.executeUpdate();
			System.out.println("Insert " + company + " succeed ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Company instert failed");

		} finally {
			connection.close();
		}

	}

	@Override
	public void removeCompany(Company company) throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "DELETE FROM COMPANY WHERE ID = ?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Deleted " + company + " succesfully ");
		} catch (SQLException e) {
			System.out.println("Connection to table Company has failed");

		} finally {
			connection.close();
		}
	}

	@Override
	public void updateCompany(Company company) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "UPDATE COMPANY SET COMP_NAME=?,PASSWORD=?,EMAIL=? WHERE ID=?";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.setLong(4, company.getId());

			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Updated " + company + " succesfully ");
		} catch (SQLException e) {
			System.out.println("Connection to table Company has failed");

		} finally {
			connection.close();
		}
	}

	@Override
	public Company getCompany(long id) throws SQLException  {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "SELECT * FROM COMPANY WHERE ID = ?";
		Company companySelected = new Company();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				
				String comp = resultSet.getString("COMP_NAME");
				String pass = resultSet.getString("PASSWORD");
				String email = resultSet.getString("EMAIL");
				companySelected = new Company(id, comp, pass, email);
				System.out.println(companySelected);
			}
			pstmt.close();
			
			
		} catch (SQLException e) {
			System.out.println("can't get connection to company table");

		} finally {
			connection.close();
		}
		return companySelected;
			}

	@Override
	public synchronized Set<Company> getAllCompanies() throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		Set<Company> set = new HashSet<>();
		String sql = "SELECT * FROM Company";
		try (Statement stm = connection.createStatement(); ResultSet resultSet = stm.executeQuery(sql)) {
			Company companySelected = new Company();
			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				String comp = resultSet.getString("COMP_NAME");
				String pass = resultSet.getString("PASSWORD");
				String email = resultSet.getString("EMAIL");

				companySelected = new Company(id, comp, pass, email);
				System.out.println(companySelected);
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new Exception("Unable To Retrieve Company Data");
		} finally {
			connection.close();
		}
		return set;
	}


}
