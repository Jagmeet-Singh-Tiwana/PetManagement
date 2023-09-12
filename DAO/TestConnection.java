package DAO;

import java.sql.*;

import java.sql.DriverManager;

public class TestConnection {

	Connection con;
	Statement stmt;
	ResultSet rs;
	public TestConnection()
	{
		System.out.println("Inside make connection");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //registration 
			
			con=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection

			}
	    catch(ClassNotFoundException e)
			{
	    		e.printStackTrace();
		    }
		catch(SQLException e)
		{
    		e.printStackTrace();
	    }
	}
		
}

