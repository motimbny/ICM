package unitTests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.SuspendableXAConnection;

public class calculatwithoutDB implements IExtentionUniversalScoring {

	public static ArrayList<report> success=new ArrayList<report>();

	@Override
	public  ArrayList<Object> getActiveSuClo(ArrayList<Object> arry) {	
		String start=(String)arry.get(0);
		String end=(String)arry.get(1);
		int[] temp=new int[success.size()];
		universalScoring set=new universalScoring();
		ArrayList<Object> send=new ArrayList<Object>();
		if(success.size()==0)
		{
			send.add(0);
			send.add(0);
			return send;
		}
	
		for(int i=0;i<success.size();i++)
		{
			if(getearlyer(success.get(i).getFrom(), start)==0)
				if(getearlyer(end, success.get(i).getTo())==0)		   
			    	temp[i]=(int) success.get(i).getNum();
		}
		send.add(set.StandardDeviation(temp));
		send.add(set.getmedian(temp));

		return send;
	}
	
	public int getearlyer(String first,String second)
	{
		Date dfirst=null,dsecond=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

             dfirst = formatter.parse(first);
             dsecond = formatter.parse(second);

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		List<Date> datesInRange = new ArrayList<>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(dfirst);
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(dsecond);
	 
	    if (calendar.before(endCalendar))
	    	return 1;
	    return 0;
	}


	
	

}
