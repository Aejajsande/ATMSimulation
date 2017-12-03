
import java.io.*;
import java.net.*;

public class SocketClient {

	public Object sendObjectToServer(Object object) {

		try {
			Socket socketToServer = new Socket("afsaccess4.njit.edu", 1792);

			ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());

			ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());

			if (object.getClass().getName().toString().equals("AuthenticationObject")) {
				AuthenticationObject authObject = (AuthenticationObject) object;
				myOutputStream.writeObject(authObject);
				object = myInputStream.readObject();
			} else {
				TransactionObject transObject = (TransactionObject) object;
				myOutputStream.writeObject(transObject);
				object = myInputStream.readObject();
			}

			myOutputStream.close();

			myInputStream.close();

			socketToServer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
