package Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class CompanyDBDAO implements CompanyDAO {
	Connection connection;

	@Override
	public void addCompany(Company company) throws Exception {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
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
	public void delCompany(Company company) throws SQLException {
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
			System.out.println("start");
			while (resultSet.next()) {
				
				String comp = resultSet.getString("COMP_NAME");
				System.out.println("comp" +comp);
				//companySelected.setId(resultSet.getLong("ID");
				//companySelected.setCompName(resultSet.getString("COMP_NAME"));
				//companySelected.setPassword(resultSet.getString("PASSWARD"));
				//companySelected.setEmail(resultSet.getString("EMAIL"));
				companySelected = new Company(id, comp, "aaa", "aa");
				System.out.println("done");
				System.out.println(companySelected);
			}
			pstmt.close();
			return companySelected;
			
		} catch (SQLException e) {
			System.out.println("can't get connection to company table");

		} finally {
			connection.close();
		}
		return companySelected;
			}
	/*
	public Company getCompany(long id) throws Exception {
		System.out.println("start");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/coupon_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
				"root", "root");
		String sql = "SELECT * FROM COMPANY WHERE ID=?";
		Company company = new Company();
		try {
			System.out.println("start2");
			Statement pstmt = connection.createStatement();
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			rs.next();
			company.setId(rs.getLong("ID"));
			company.setCompName(rs.getString(2));
			company.setPassword(rs.getString(3));
			company.setEmail(rs.getString(4));
			System.out.println("start3");
		} catch (SQLException e) {
			throw new Exception("Unable To Retrieve Company Data");
		} finally {
			connection.close();
		}
		System.out.println("start4");
		return company;

	}
*/
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
