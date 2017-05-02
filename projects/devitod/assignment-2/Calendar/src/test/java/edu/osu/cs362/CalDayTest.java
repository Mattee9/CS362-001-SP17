package edu.osu.cs362;
/**
*  This class provides a basic set of test cases for the
*  CalDay class.
*/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class CalDayTest {
	/**
	* Test that tests for the span on one day
	*/
	 @Test
	  public void test01()  throws Throwable  {
	     Calendar now = Calendar.getInstance();
       GregorianCalendar cal = new GregorianCalendar();
	     CalDay day1 = new CalDay(cal);


        int startHour=15;
        int startMinute= 30;
        int startDay = 11;
        int startMonth = 1;
        int startYear = 2011;
        String title="Birthday Party";
        String description="This is my birthday party.";
        //Construct a new Appointment object with the initial data
        Appt appt = new Appt(startHour,
                startMinute ,
                startDay ,
                startMonth ,
                startYear ,
                title,
                description);
        Appt appt2 = new Appt(startHour - 1,
                startMinute - 1,
                startDay,
                startMonth,
                startYear ,
                title,
                description);
        assertTrue(appt.getValid());
        day1.addAppt(appt);
        assertTrue(day1.isValid());
        day1.addAppt(appt2);
        assertTrue(day1.isValid());
	 }
	 /* Test for making sure that "today" in the app is "today" in the real world */
	 @Test
	 public void test02() throws Throwable {
	     
         Calendar rightNow = Calendar.getInstance();
         
         int month = rightNow.get(Calendar.MONTH)+1;
         int year = rightNow.get(Calendar.YEAR);
         int day = rightNow.get(Calendar.DAY_OF_MONTH);
         	GregorianCalendar today = new GregorianCalendar(year,month,day);

         CalDay calDay = new CalDay(today);
         
         assertTrue(calDay.isValid());
     }
	 
	 @Test
	  public void test03()  throws Throwable  {
			GregorianCalendar cal = new GregorianCalendar();
			CalDay cal_day = new CalDay(cal);
		 	Appt new_appt = new Appt(13, 30, 10, 4, 2017,
						"Birthday Party",
						"This is my birthday party.");

			
			cal_day.addAppt(new_appt);

			assertTrue(new_appt.getValid());
			assertEquals(1, cal_day.getSizeAppts());				
	 
		  }
}