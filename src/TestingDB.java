import java.sql.*;

class TestingDB {
	public static void main(String[] Args) {

		String url = "jdbc:oracle:thin:@prophet.njit.edu:1521:course";
		String ucid = "ams244";	//your ucid
		String dbpassword = "VHoYAxTx";	//your Oracle password


		System.out.println("This example program will create a table in Oracle and "+
			"populate that table with three rows of sample data. The program " +
			"will then query the database for the contents of the table and " +
			"dispay the result.");

		System.out.println("Starting test . . .");
		
		
		System.out.println("Loading driver . . .");
		try {
			//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		}
		catch (Exception e) {
			System.err.println("Unable to load driver.");
			e.printStackTrace();
		}
		System.out.println("Driver loaded.");
		System.out.println("Establishing connection . . . ");
		try {
			Connection conn = DriverManager.getConnection (url, ucid, dbpassword);

			System.out.println("Connection established.");			
			System.out.println("Creating a Statement object . . . ");

			Statement stmt = conn.createStatement();
			System.out.println("Statement object created.");
//			try{
//			//	stmt.executeUpdate("DROP TABLE users");
//			}catch(Exception e){}
//			System.out.println("Old table dropped (if it existed).");
//
//			System.out.println("Creating a table . . .");
//			stmt.executeUpdate("CREATE TABLE users("+
//				"idnum INTEGER NOT NULL, "+
//				"userid VARCHAR(100) UNIQUE NOT NULL, "+
//				"fullname VARCHAR(100) NOT NULL, "+
//				"email VARCHAR(100) NOT NULL, "+
//				"notes VARCHAR(100))");
//			System.out.println("Table created.");
//
//			System.out.println("Inserting data in table . . .");
//
//			stmt.executeUpdate("INSERT INTO users "+
//				"VALUES("+1+",'john', 'john smith', 'john@njit.edu', 'blah, blah . . ')");
//			stmt.executeUpdate("INSERT INTO users (idnum, userid, fullname, email, notes) "+
//				"VALUES("+2+",'jane', 'jane doe', 'jane@njit.edu', 'blah, blah . . ')");
//			stmt.executeUpdate("INSERT INTO users (idnum, userid, fullname, email, notes) "+
//				"VALUES("+3+",'zip', 'zip zippy', 'zippy@njit.edu', 'blah, blah . . ')");
//
//			System.out.println("Inserted data.");
//
//			System.out.println("Querying table  . . . ");
			
			ResultSet rs = stmt.executeQuery("SELECT * from Charter");
			while (rs.next()) {
System.out.println(rs);
				//				System.out.print(rs.getString("idnum")+"\t"+rs.getString("userid")+"\t"+rs.getString("fullname")+"\t"+
//					rs.getString("email")+"\t"+rs.getString("notes"));
//				System.out.println();			}
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("If you see three rows of data above, the test example sucessfully completed.");
		}
		catch (SQLException E) {
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState:     " + E.getSQLState());
			System.out.println("VendorError:  " + E.getErrorCode());
		}
	}}

