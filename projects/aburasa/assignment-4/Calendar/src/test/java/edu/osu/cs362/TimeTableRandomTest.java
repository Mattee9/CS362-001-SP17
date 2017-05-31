package edu.osu.cs362;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	private static final int LISTSIZE=10;


	/**
     * Generate Random Tests that tests TimeTable Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 System.out.println("Start testing...");


		 for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			 long randomseed =System.currentTimeMillis();
			 //			System.out.println(" Seed:"+randomseed );
			 Random random = new Random(randomseed);
			 TimeTable timeTable = new TimeTable();
			 LinkedList<Appt> appts = new LinkedList<Appt>();
			 Appt savedAppt = null;
			 for (int i=0; i<LISTSIZE; i++){
			 	int startMinute = ValuesGenerator.getRandomIntBetween(random, -30, 90);
			 	int startHour = ValuesGenerator.getRandomIntBetween(random, -12, 24);
			 	int startDay = ValuesGenerator.getRandomIntBetween(random, -15, 45);
			 	int startMonth = ValuesGenerator.getRandomIntBetween(random, -6, 18);
			 	int startYear = ValuesGenerator.getRandomIntBetween(random, 0, 5000);
			 	String randTitle = ValuesGenerator.getString(random);
			 	String randDescription = ValuesGenerator.getString(random);
			 	Appt newAppt = new Appt(startHour, startMinute, startDay, startMonth, startYear, randTitle, randDescription);
			 	if(i==LISTSIZE-1){
			 		savedAppt = newAppt;
				}
				appts.add(newAppt);
			 }

			 for (int j = 0; j<6; j++){
			 	int choice = ValuesGenerator.getRandomIntBetween(random, 1, 3);
			 	switch (choice){
					case 1:
						timeTable.deleteAppt(appts, savedAppt);
						break;
					case 2:
						timeTable.deleteAppt(appts, null);
						break;
					case 3:
						timeTable.deleteAppt(null, savedAppt);
						break;
				}
			 }

			 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			 if((iteration%10000)==0 && iteration!=0 )
				 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

		 }

		 System.out.println("Done testing...");

	 }
	
}