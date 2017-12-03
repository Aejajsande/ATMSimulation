import java.sql.*;

public class DBConnection {
	String dbUsername, dbPassword, url;
	Connection con;
	Statement s1;
	ResultSet rs;

	public DBConnection() {
		try {
			try {
	//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (Exception e) {
				System.err.println("Unable to load driver.");
				e.printStackTrace();
			}

			dbUsername = "ams244";
			dbPassword = "VHoYAxTx";
			url = "jdbc:oracle:thin:@prophet.njit.edu:1521:course";
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
			s1 = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AuthenticationObject createNewAccount(AuthenticationObject authObject) {
		try {
			int rows = 0;
			rs = s1.executeQuery("Select * from UserInfo");
			while (rs.next()) {
				rows++;
			}

			String savingNumber = "", checkingNumber = "";
			s1.executeUpdate("Insert into UserInfo values ('" + authObject.getCustomerID() + "','"
					+ authObject.getName() + "','" + authObject.getPassword() + "','saving',50," + (rows + 1) + ")");
			rs = s1.executeQuery("Select Acc_Number from UserInfo where Cust_Id = '" + authObject.getCustomerID()
					+ "'and AccountType = 'saving'");
			if (rs != null) {
				while (rs.next()) {
					savingNumber = rs.getString("Acc_Number");
				}
			}
			s1.executeUpdate("Insert into UserInfo values ('" + authObject.getCustomerID() + "','"
					+ authObject.getName() + "','" + authObject.getPassword() + "','checking',50," + (rows + 2) + ")");
			rs = s1.executeQuery("Select Acc_Number from UserInfo where Cust_Id = '" + authObject.getCustomerID()
					+ "'and AccountType = 'checking'");
			if (rs != null) {
				while (rs.next()) {
					checkingNumber = rs.getString("Acc_Number");

				}
			}

			authObject.setMessage("The saving account number is " + savingNumber + "and checking number is"
					+ checkingNumber + "Both have $50 Balance");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authObject;

	}

	public AuthenticationObject authorizeUser(AuthenticationObject authObject) {
		try {
			rs = s1.executeQuery(
					"Select UserPassword from Userinfo where Cust_Id ='" + authObject.getCustomerID() + "'");
			if (rs != null) {
				while (rs.next()) {
					if (rs.getString("UserPassword").equals(authObject.getPassword())) {
						authObject.setMessage("User Authenticated");
						authObject.setIsauthenticated(true);
					} else {
						authObject.setMessage("Authentication failed");
						authObject.setIsauthenticated(false);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authObject;
	}

	public String checkBalance(String custId, String accountType) {
		return getBalanceInAccount(custId, accountType).toString();
	}

	public String depositCash(String custId, String accountType, Float amount) {
		Float existingAmount = getBalanceInAccount(custId, accountType);
		Float newAmmount = amount + existingAmount;
		setBalanceInAccount(custId, accountType, newAmmount.toString());
		return "Amount deposited . New Balance in " + accountType + "is :" + getBalanceInAccount(custId, accountType);
	}

	public String withDrawMoney(String custId, String accountType, Float amount) {

		Float existingAmount = getBalanceInAccount(custId, accountType);
		Float newAmmount = existingAmount - amount;
		if (newAmmount < 0) {
			return "Existing balance is low";
		} else {
			setBalanceInAccount(custId, accountType, newAmmount.toString());
			return "Amount withdrawn . New Balance in " + accountType + "is :"
					+ getBalanceInAccount(custId, accountType);
		}
	}

	public void transferFunds() {

	}

	public Float getBalanceInAccount(String custId, String accountType) {
		String ammount = "";
		try {
			rs = s1.executeQuery("Select Amount from UserInfo where Cust_Id = '" + custId + "' and AccountType = '"
					+ accountType + "'");
			while (rs.next()) {
				ammount = rs.getString("Amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Float.parseFloat(ammount);
	}

	public void setBalanceInAccount(String custId, String accountType, String amount) {
		String ammount = "";
		try {
			s1.executeUpdate("update UserInfo set Amount = " + amount + " where Cust_ID = '" + custId
					+ "' and AccountType = '" + accountType + "'");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String transferMoney(String custId, String transferType, Float amount) {
		String savingMoney, checkingMoney;
		if (transferType.equalsIgnoreCase("saving to checking")) {
			String bal = withDrawMoney(custId, "saving", amount);
			if (bal.equalsIgnoreCase("Existing balance is low")) {
				return "Balance Low";
			} else {
				savingMoney = getBalanceInAccount(custId, "saving").toString();
				depositCash(custId, "checking", amount);
				checkingMoney = getBalanceInAccount(custId, "checking").toString();
				return "Transfer done :\n saving balance : " + savingMoney + "and checking balance :" + checkingMoney;
			}
		} else {
			String bal = withDrawMoney(custId, "checking", amount);
			if (bal.equalsIgnoreCase("Existing balance is low")) {
				return "Balance Low";
			} else {
				checkingMoney = getBalanceInAccount(custId, "checking").toString();
				depositCash(custId, "saving", amount);
				savingMoney = getBalanceInAccount(custId, "saving").toString();
				return "Transfer done :\n saving balance : " + savingMoney + "and checking balance :" + checkingMoney;
			}
		}
	}

}