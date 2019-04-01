package Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import DB.Database;

public class CompanyDBDAO implements CompanyDAO {
	Connection con;

	@Override
	public void addCompany(Company company) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "INSERT INTO COMPANY (COMP_NAME, PASSWORD, EMAIL) VALUES (?,?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());

			pstmt.executeUpdate();
			System.out.println("Insterted " + company + " succesfully ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Company instert failed");

		} finally {
			connection.close();
		}

	}
	@Override
	public void delCompany(Company company) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "DELETE FROM COMPANY WHERE id=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			System.out.println(company);
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			connection.commit();
			System.out.println("Deleted " + company + " succesfully ");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new Exception("Database error");
			}
			throw new Exception("failed to remove product");
		} finally {
			connection.close();
		}
	}
	@Override
	/*public void delCompany(Company company) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String pre1 = "DELETE FROM COMPANY WHERE id=?";

		try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
			con.setAutoCommit(false);
			pstm1.setLong(1, company.getId());
			pstm1.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new Exception("Database error");
			}
			throw new Exception("failed to remove product");
		} finally {
			con.close();
		}
	}
*/
	@Override
	public void updateCompany(Company company) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try (Statement stm = con.createStatement()) {
			String sql = "UPDATE COMPANY " + " SET compName='" + company.getCompName() + "', password='"
					+ company.getPassword() + "', email='" + company.getEmail() + "' WHERE ID=" + company.getId();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			throw new Exception("Company update failed");
		}

	}

	public void getCompany(Company company) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		//Company company = new Company();
		try (Statement stm = connection.createStatement()) {
			String sql = "SELECT * FROM COMPANY WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			company.setId(rs.getLong(1));
			company.setCompName(rs.getString(2));
			company.setPassword(rs.getString(3));
			company.setEmail(rs.getString(4));

		} catch (SQLException e) {
			throw new Exception("Unable To Retrieve Company Data");
		} finally {
			con.close();
		}
		return company;

	}

	@Override
	public synchronized Set<Company> getAllCompanies() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<Company> set = new HashSet<>();
		String sql = "SELECT id FROM Company";
		try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			while (rs.next()) {
				long id = rs.getLong(1);
				String compName = rs.getString(1);
				String passowrd = rs.getString(1);
				String email = rs.getString(1);

				set.add(new Company(id, compName, passowrd, email));
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new Exception("Unable To Retrieve Company Data");
		} finally {
			con.close();
		}
		return set;
	}
}
