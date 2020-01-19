package unitTests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertWithDB implements IExtentionUniversalSScoring{

	public void insert( Connection connection,String start,String end,String type)
	{
		PreparedStatement req;
		try {
			req = connection.prepareStatement("INSERT INTO reports VALUES(?,?,?)");
			req.setString(1, start);
			req.setString(2, end);
			req.setString(3,type);
			req.executeUpdate();
			req.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
