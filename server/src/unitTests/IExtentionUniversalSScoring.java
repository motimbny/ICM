package unitTests;

import java.sql.Connection;

public interface IExtentionUniversalSScoring {
	
	public void insert(Connection connection,String start,String end,String type);
}
