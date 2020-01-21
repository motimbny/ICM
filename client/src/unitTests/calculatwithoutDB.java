package unitTests;

import java.util.ArrayList;
import java.util.Map;

public class calculatwithoutDB implements IExtentionUniversalScoring {

	public static ArrayList<Object> success=new ArrayList<Object>();

	@Override
	public  ArrayList<Object> getActiveSuClo(ArrayList<Object> arry) {	
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
			temp[i]=(int) success.get(i);
		}
		send.add(set.StandardDeviation(temp));
		send.add(set.getmedian(temp));

		return send;
	}


	
	

}
