package Customer;

public class Customer {
	private long id;
	private String cust_name;
	private String password;

	public Customer(long id, String cust_name, String password) {
		setId(id);
		setCust_name(cust_name);
		setPassword(password);

	}

	public Customer() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cust_name=" + cust_name + ", password=" + password + "]";
	}

}
