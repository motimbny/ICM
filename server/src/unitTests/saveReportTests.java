package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class saveReportTests {


	public insertWithOutDB test=new insertWithOutDB();
	@Test
	public void regularinsert() {
		insertWithOutDB.reports.clear();
		test.insert(null, "2020-01-01", "2020-01-02", "Activity");
		System.out.println(insertWithOutDB.reports.size());
		assertEquals(1, insertWithOutDB.reports.size());
	}
	@Test
	public void insertsame() {
		
		insertWithOutDB.reports.clear();
		test.insert(null, "2020-01-01", "2020-01-02", "Activity");
		test.insert(null, "2020-01-01", "2020-01-02", "Activity");
		
		System.out.println(insertWithOutDB.reports.size());
		assertEquals(1, insertWithOutDB.reports.size());
	}
	@Test
	public void insertSameDateButDiffType() {
		
		insertWithOutDB.reports.clear();
		test.insert(null, "2020-01-01", "2020-01-02", "Performence");
		test.insert(null, "2020-01-01", "2020-01-02", "Activity");
		
		
		System.out.println(insertWithOutDB.reports.size());
		assertEquals(2, insertWithOutDB.reports.size());
	}


}
