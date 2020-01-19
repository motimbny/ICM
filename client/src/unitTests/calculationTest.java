package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class calculationTest {


	public universalScoring test;
	public calculatwithoutDB stub=new calculatwithoutDB();
	
	//check surveyScore function with too many args(more then three)
	@Test
	public void testevenlenth()
	{
		calculatwithoutDB.success.clear();
		calculatwithoutDB.success.add((int)0);
		calculatwithoutDB.success.add((int)1);
		calculatwithoutDB.success.add((int)2);
		calculatwithoutDB.success.add((int)3);
		calculatwithoutDB.success.add((int)4);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-01-10","2020-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(2,med);
		assertEquals((float)Math.sqrt(2),dev);
	}
	@Test
	public void testoddlenth()
	{
		calculatwithoutDB.success.clear();
		calculatwithoutDB.success.add((int)0);
		calculatwithoutDB.success.add((int)1);
		calculatwithoutDB.success.add((int)2);
		calculatwithoutDB.success.add((int)3);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-01-10","2020-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(2,med);
		assertEquals((float)Math.sqrt(1.25),dev);
	}
	
	@Test
	public void testOneReq()
	{
		calculatwithoutDB.success.clear();

		calculatwithoutDB.success.add((int)3);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-01-10","2020-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(3,med);
		assertEquals((float)Math.sqrt(0),dev);
	}
	


	

	
	

}
