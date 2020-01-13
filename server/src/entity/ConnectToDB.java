package entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connection to the DataBase
 * @author SHIRA
 *
 */
public class ConnectToDB 
{
	private String password;
	private String UserName;
	private String BaseName;
	public ConnectToDB(String password,String UserName,String BaseName)
	{
		this.UserName=UserName;
		this.password=password;
		this.BaseName=BaseName;
	}
	
	/**
	 * Connect.
	 *
	 * @return the connection
	 */
	public Connection  Connect()
	{
		Connection con=null;
		try 
  		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("> Driver definition succeed");
        } catch (Exception ex) {
        	System.out.println("> Driver definition failed");
        	 }
        
        try 
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+BaseName+"?serverTimezone=IST",UserName,password);
            System.out.println("> SQL connection succeed");
           
     	} catch (SQLException ex) 
     	    {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
        return con;
	}
}
