
public class ClientInterface {
	static String userId = "";

	public boolean constructAndSendAuthenticationObject(String customerId, String password) {
		SocketClient client = new SocketClient();
		AuthenticationObject authObject = new AuthenticationObject();
		authObject.setCustomerID(customerId);
		authObject.setPassword(password);
		authObject.setMessage("");
		authObject.setName("newUser");
		authObject.setIsauthenticated(false);
		authObject = (AuthenticationObject) client.sendObjectToServer(authObject);
		userId = authObject.getCustomerID();

		return authObject.isIsauthenticated();
	}

	public String createNewUser(String name, String username, String password) {
		SocketClient client = new SocketClient();
		AuthenticationObject authObject = new AuthenticationObject();
		authObject.setName(name);
		authObject.setCustomerID(username);
		authObject.setPassword(password);
		authObject = (AuthenticationObject) client.sendObjectToServer(authObject);

		return authObject.getMessage();
	}

	public String withdrawAmountFromAccount(String amount, String account) {
		TransactionObject transactionObject = new TransactionObject();
		SocketClient client = new SocketClient();
		transactionObject.setId(userId);
		transactionObject.setMessage("withdraw");
		transactionObject.setType(account);
		transactionObject.setAmount(Float.parseFloat(amount));
		transactionObject = (TransactionObject) client.sendObjectToServer(transactionObject);
		return transactionObject.getMessage();
	}

	public String depositMoneyToAccount(String amount, String account) {
		TransactionObject transactionObject = new TransactionObject();
		SocketClient client = new SocketClient();
		transactionObject.setId(userId);
		transactionObject.setMessage("deposit");
		transactionObject.setType(account);
		transactionObject.setAmount(Float.parseFloat(amount));
		transactionObject = (TransactionObject) client.sendObjectToServer(transactionObject);
		return transactionObject.getMessage();
	}

	public String CheckBalanceInAccount(String account) {
		TransactionObject transactionObject = new TransactionObject();
		SocketClient client = new SocketClient();
		transactionObject.setId(userId);
		transactionObject.setMessage("balance");
		transactionObject.setType(account);
		transactionObject = (TransactionObject) client.sendObjectToServer(transactionObject);
		return transactionObject.getMessage();
	}

	public String transferMoney(String account, String amount) {
		TransactionObject transactionObject = new TransactionObject();
		SocketClient client = new SocketClient();
		transactionObject.setId(userId);
		transactionObject.setMessage("transfer");
		transactionObject.setType(account);
		transactionObject.setAmount(Float.parseFloat(amount));
		transactionObject = (TransactionObject) client.sendObjectToServer(transactionObject);
		return transactionObject.getMessage();
	}

	public void logout() {
		userId = "";
	}

}
