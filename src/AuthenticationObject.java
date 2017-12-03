import java.io.Serializable;
import java.net.*;

public class AuthenticationObject implements Serializable {

	private String customerID;
	private String password;
	private boolean isauthenticated;
	private String message;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public boolean isIsauthenticated() {
		return isauthenticated;
	}

	public void setIsauthenticated(boolean isauthenticated) {
		this.isauthenticated = isauthenticated;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
