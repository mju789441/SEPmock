import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OoooSmartClockTest {
	DateFactory factory;
	OoooSetTimeZoneDialogstub stubDialog;
	OoooSmartClock clock;
	@Before
	public void setUp() throws Exception {
		factory=new DateFactory();
		stubDialog=new OoooSetTimeZoneDialogstub();
		clock=new OoooSmartClock(stubDialog,factory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void GetCurrentTimeStampTest() {

		
		//test if get the CurrentTIme
		assertEquals( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),clock.getCurrentTimeStamp());


	}
	@Test 
	public void GetLocalTimeStampTest(){
			factory.set("2009-09-22 12:00:59");
			clock.setStub(stubDialog, factory);
			
			assertEquals("2009-09-22 NOON",clock.getLocalCurrentTimeStamp());
			
			factory.set("2009-09-22 12:00:00");
			clock.setStub(stubDialog, factory);
			
			assertEquals("2009-09-22 NOON",clock.getLocalCurrentTimeStamp());
			
			factory.set("2009-10-10 12:00:00");
			clock.setStub(stubDialog, factory);
			
			assertEquals("2009-10-10 DOUBLE-TEN NOON",clock.getLocalCurrentTimeStamp());
			
			
			factory.set("2009-10-10 12:02:00");
			clock.setStub(stubDialog, factory);
			
			assertEquals("2009-10-10 DOUBLE-TEN 12:02:00",clock.getLocalCurrentTimeStamp());
			
			
			factory.set("2009-08-08 12:02:00");
			clock.setStub(stubDialog, factory);
			
			assertEquals("2009-08-08 FATHER'S DAY 12:02:00",clock.getLocalCurrentTimeStamp());
			
			
			factory.set("2201-12-25 00:01:00");
			clock.setStub(stubDialog, factory);
	
			assertEquals("2201-12-25 X'MAS MIDNIGHT",clock.getLocalCurrentTimeStamp());
			
			factory.set("2201-12-25 16:37:08");
			clock.setStub(stubDialog, factory);
	
			assertEquals("2201-12-25 X'MAS 16:37:08",clock.getLocalCurrentTimeStamp());
			
		
	} 
	@Test //test if  set function timezone work 
	public void  TimeZone(){
		clock.setTimeZone(0);
		System.out.println(clock.getCurrentTimeStamp());
		clock.setTimeZone(8);
		System.out.println(clock.getCurrentTimeStamp());
		clock.setTimeZone(16);
		System.out.println(clock.getCurrentTimeStamp());
		
	}
	@Test
	public void NODATAtest(){
		stubDialog.setindex(-99);
		 clock=new OoooSmartClock(stubDialog,factory);


		clock.setTimeZone();

		assertFalse(clock.dataExist);

		stubDialog.setindex(9);
		clock=new OoooSmartClock(stubDialog,factory);


		clock.setTimeZone();
		assertTrue(clock.dataExist);



	}
	class OoooSetTimeZoneDialogstub implements IOoooSetTimeZoneDialog{
		private int index=-1;

		public int getindex() throws NODATA{

			if (index<0 || index >24)
				throw new NODATA();

			return index;
		}
		public void setindex(int x){
			this.index=x;

		}

	}


}
