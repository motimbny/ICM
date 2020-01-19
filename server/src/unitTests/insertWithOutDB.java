package unitTests;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import entity.recentreport;

public class insertWithOutDB implements IExtentionUniversalSScoring
{
	int flag=0;
	public static ArrayList<recentreport> reports=new ArrayList<recentreport>();
	@Override
	public void insert(Connection connection,String start, String end,String type) {
		if(reports.size()==0)
			reports.add(new recentreport(start, end, type));
		else
		{
			for(recentreport rep:reports)
			{
			
				if(rep.comper(new recentreport(start,end,type)))
					flag=1;
			}
			if(flag==0)
				reports.add(new recentreport(start, end, type));
		}
	
		
	}

}
