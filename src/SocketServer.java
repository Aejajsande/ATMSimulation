import java.io.*;
import java.net.*;

public class SocketServer {
	public static void main(String[] arg) {

		AuthenticationObject authObject = null;
		TransactionObject transObject = null;
		String customerId, password, accountType, name;
		int accountNumber;
		float amount;
		try {
			ServerSocket myServerSocket = new ServerSocket(1792);
			while (true) {
				Socket incoming = myServerSocket.accept();

				ObjectOutputStream myOutputStream = new ObjectOutputStream(incoming.getOutputStream());

				ObjectInputStream myInputStream = new ObjectInputStream(incoming.getInputStream());
				Object object = myInputStream.readObject();

				if (object.getClass().getName().equals("AuthenticationObject")) {
					DBConnection connect = new DBConnection();

					authObject = (AuthenticationObject) object;
					if (authObject.getName().equals("newUser")) {
						authObject = connect.authorizeUser(authObject);

					} else {
						authObject = connect.createNewAccount(authObject);

					}
					myOutputStream.writeObject(authObject);
				} else {
					String message = "";
					DBConnection connect = new DBConnection();
					transObject = (TransactionObject) object;
					switch (transObject.getMessage()) {
					case "withdraw": {
						message = connect.withDrawMoney(transObject.getId(), transObject.getType(),
								transObject.getAmount());
					}
						break;
					case "deposit": {
						message = connect.depositCash(transObject.getId(), transObject.getType(),
								transObject.getAmount());
					}
						break;
					case "balance": {
						message = connect.checkBalance(transObject.getId(), transObject.getType());
					}
						break;
					case "transfer": {
						message = connect.transferMoney(transObject.getId(), transObject.getType(),
								transObject.getAmount());
					}
					}
					transObject.setMessage(message);
					myOutputStream.writeObject(transObject);
				}

			}

			/*
			 * System.out.println("Message received : " +
			 * myObject.getMessage());
			 * 
			 * myObject.setMessage("Got it!");
			 * 
			 * System.out.println("Message sent : " + myObject.getMessage());
			 */

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
