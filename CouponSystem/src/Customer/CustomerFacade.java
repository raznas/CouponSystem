package Customer;

import java.sql.SQLException;
import java.util.Set;

import Customer.Customer;

public class CustomerFacade {
	private CustomerDBDAO prodDAO = new CustomerDBDAO();
	private Customer customer;
	
	public CustomerFacade (Customer customer) {
		this.customer = customer;
	}
	
	public CustomerFacade() {
		
	}
	
	public void createCustomer(Customer customer) throws Exception {
		prodDAO.createCustomer(customer);
		
	}

	public void removeCustomer(Customer customer) throws Exception {
		prodDAO.removeCustomer(customer);
	}

	public void updateCustomer(Customer customer, String newCustName, String newPassword) throws Exception {
		customer.setCust_name(newCustName);
		customer.setPassword(newPassword);
		prodDAO.updateCustomer(customer);
	}

	public Customer getCustomer(long id) throws Exception {
				return prodDAO.getCustomer(id);
	}

	public Set<Customer> getAllCustomers() throws Exception {
		// ProductDBDAO comDAO=new ProductDBDAO();
		return prodDAO.getAllCustomers();
	}
}
