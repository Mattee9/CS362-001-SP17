package edu.osu.cs362;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	
	
	public static String RandomSelectMethod(Random random){
      String[] methodArray = new String[] {"setTitle","setDescription"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
    }
	
	
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 
	 @Test
	  public void randomtest()  throws Throwable  {
		 
		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing on CalDay class...");
		 TimeUnit.SECONDS.sleep(1);	
			
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {				
				long randomseed = System.currentTimeMillis();
				Random random = new Random(randomseed);
				
				
				GregorianCalendar cal = new GregorianCalendar();
				CalDay cal_day = new CalDay(cal);
	
	
				int randHour = random.nextInt(24);
				int randHour2 = random.nextInt(24);
				int randMinute = random.nextInt(60);
				int randMinute2 = random.nextInt(60);
				int randDay = random.nextInt(32);
				int randDay2 = random.nextInt(32);
				int randMonth = random.nextInt(13);
				int randMonth2 = random.nextInt(13);
				int randYear = random.nextInt();
				int randYear2 = random.nextInt();
				String randTitle = (String) ValuesGenerator.getString(random);
				String randTitle2 = (String) ValuesGenerator.getString(random);
				String randDescription = (String) ValuesGenerator.getString(random);
				String randDescription2 = (String) ValuesGenerator.getString(random);
				
				
				Appt appt = new Appt(randHour, randMinute,randDay,randMonth,randYear,randTitle,randDescription);
				
				Appt appt2 = new Appt(randHour2,randMinute2,randDay2,randMonth2,randYear2,randTitle2,randDescription2);					 
				
				
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					   if (methodName.equals("setTitle")){
						   String newTitle=(String) ValuesGenerator.getString(random);
						   appt.setTitle(newTitle);						   
						}
						if (methodName.equals("setDescription")){
						   String newDescription=(String) ValuesGenerator.getString(random);
						   appt.setDescription(newDescription);						   
						}
					
				}
				
				cal_day.addAppt(appt);
				cal_day.addAppt(appt2);
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			       if((iteration%10000)==0 && iteration!=0 )
			             System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
	 
	 
		 System.out.println("Done testing CalDay class...");
	 }

	
}