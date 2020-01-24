package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class calculationTest {


	public universalScoring test=new universalScoring(new calculatwithoutDB()) ;
	public calculatwithoutDB stub=new calculatwithoutDB();
	
	//check surveyScore function with too many args(more then three)
	@Test
	public void emptyreq()
	{
		calculatwithoutDB.success.clear();
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-01-10","2020-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(0,med);
		assertEquals(0,dev);
	}
	@Test
	public void testevenlenth()
	{
		calculatwithoutDB.success.clear();
		report f1=new report("2027-01-10","2028-01-22",0);
		report f2=new report("2027-01-10","2028-01-22",1);
		report f3=new report("2027-01-10","2028-01-22",2);
		report f4=new report("2027-01-10","2028-01-22",3);
		report f5=new report("2027-01-10","2028-01-22",4);
		calculatwithoutDB.success.add(f1);
		calculatwithoutDB.success.add(f2);
		calculatwithoutDB.success.add(f3);
		calculatwithoutDB.success.add(f4);
		calculatwithoutDB.success.add(f5);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2027-01-10","2029-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(2,med);
		assertEquals((float)Math.sqrt(2),dev);
	}
	@Test
	public void testoddlenth()
	{		calculatwithoutDB.success.clear();
	report f1=new report("2026-01-10","2026-01-22",0);
	report f2=new report("2026-01-10","2026-01-22",1);
	report f3=new report("2026-01-10","2026-01-22",2);
	report f4=new report("2026-01-10","2026-01-22",3);
	calculatwithoutDB.success.add(f1);
	calculatwithoutDB.success.add(f2);
	calculatwithoutDB.success.add(f3);
	calculatwithoutDB.success.add(f4);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2026-01-10","2026-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(2,med);
		assertEquals((float)Math.sqrt(1.25),dev);
	}
	
	@Test
	public void testOneReq()
	{
		calculatwithoutDB.success.clear();
		report f4=new report("2028-01-10","2029-01-22",3);
		calculatwithoutDB.success.add(f4);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2028-01-10","2029-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(3,med);
		assertEquals((float)Math.sqrt(0),dev);
	}
	@Test
	public void testDiffrentStart()
	{
		calculatwithoutDB.success.clear();
		report f4=new report("2020-01-10","2020-01-22",3);
		calculatwithoutDB.success.add(f4);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-02-10","2020-01-22");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(0,med);
		assertEquals((float)Math.sqrt(0),dev);
	}
	@Test
	public void testDiffrentEnd()
	{
		calculatwithoutDB.success.clear();
		report f4=new report("2020-01-10","2020-01-22",3);
		calculatwithoutDB.success.add(f4);
		ArrayList<Object> get=universalScoring.makeActiveSuClo("2020-01-10","2020-01-10");
		Object med=get.get(1);
		Object dev=get.get(0);
		assertEquals(0,med);
		assertEquals((float)Math.sqrt(0),dev);
	}


	

	
	

}
