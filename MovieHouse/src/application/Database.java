package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database 
{
	private static final String url="jdbc:mysql://localhost:3306/movie_store";
	private static final String username="root";
	private static final String password="";
	private static  Connection con=null;
	private static  Statement st=null;
	
	
	
	
	private static Connection getConnection()
	{
		
		String driver= "com.mysql.jdbc.Driver" ;
		
		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("error is  occured 2");
			//ex.printStackTrace();
		}
		
		
		try
		{
			con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			System.out.println("error is  occured 1");
			//e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	public static Statement getStatement()
	{
		con=getConnection();
		try
		{
			st=con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("error is  occured 1");
			//e.printStackTrace();
		}
		return st;
	}
	
	
}