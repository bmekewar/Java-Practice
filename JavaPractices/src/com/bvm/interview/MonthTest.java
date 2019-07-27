package com.bvm.interview;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MonthTest {
	public static void main(String[] args) {
		// Use Month.JULY as a method argument
		LocalDate ld1 = LocalDate.of(2012, Month.JULY, 1);
		// Derive a Month from a local date
		Month m1 = Month.from(ld1);
		// Create a Month from an int value 2
		Month m2 = Month.of(2);
		// Get the next month from m2
		Month m3 = m2.plus(1);
		// Get the Month from a local date
		Month m4 = ld1.getMonth();
		// Convert an enum constant to an int
		int m5 = m2.getValue();
		System.out.format("%s, %s, %s, %s, %d%n", m1, m2, m3, m4, m5);
		
		LocalDate ld = LocalDate.of(2012, 5, 10);
		// Extract the day-of-week from a LocalDate
		DayOfWeek dw1 = DayOfWeek.from(ld); // THURSDAY
		// Get the int value of the day-of-week
		int dw11 = dw1.getValue(); // 4
		// Use the method of the LocalDate class to get day-of-week
		DayOfWeek dw12 = ld.getDayOfWeek(); // THURSDAY
		// Obtain a DayOfWeek instance using an int value
		DayOfWeek dw2 = DayOfWeek.of(7); // SUNDAY
		// Add one day to the day-of-week to get the next day
		DayOfWeek dw3 = dw2.plus(1); // MONDAY
		// Get the day-of-week two days ago
		DayOfWeek dw4 = dw2.minus(2); // FRIDAY
		
		System.out.format("%s, %s, %s, %s, %s%n", dw1, dw12, dw2, dw3, dw4);
		
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Current Date Time: " + now);
		System.out.println("Year: " + now.get(ChronoField.YEAR));
		System.out.println("Month: " + now.get(ChronoField.MONTH_OF_YEAR));
		System.out.println("Day: " + now.get(ChronoField.DAY_OF_MONTH));
		System.out.println("Hour-of-day: " + now.get(ChronoField.HOUR_OF_DAY));
		System.out.println("Hour-of-AMPM: " + now.get(ChronoField.HOUR_OF_AMPM));
		System.out.println("AMPM-of-day: " + now.get(ChronoField.AMPM_OF_DAY));
		LocalDate today = LocalDate.now();
		System.out.println("Current Date : " + today);
		System.out.println("LocalDate supports year: " + today.isSupported(ChronoField.YEAR));
		System.out.println("LocalDate supports hour-of-day: " + today.isSupported(ChronoField.HOUR_OF_DAY));
		System.out.println("Year is supported by LocalDate: " + ChronoField.YEAR.isSupportedBy(today));
		System.out.println("Hour-of-day is supported by LocalDate: " +
		ChronoField.HOUR_OF_DAY.isSupportedBy(today));
		
		
		/*SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss"); //$NON-NLS-1$
		//sdf.setLenient(false);
		try {
			Date date = sdf.parse("2016-01-07");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	/*	DateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		try {

		    String reformattedStr = myFormat.format(fromUser.parse("2016-01-07"));
		    Date d = (Date)myFormat.parse(reformattedStr);
		    GregorianCalendar g = new GregorianCalendar();
		    g.setGregorianChange(d);
		    //System.out.println(reformattedStr);
		    System.out.println(g);
		    System.out.println(d);
		} catch (ParseException e) {
		    e.printStackTrace();
		}*/
		try {
			DateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
			Date d = (Date) fromUser.parse("2016-01-07");

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			Date d1 = formatter.parse(formatter.format(d));
			Instant date = d1.toInstant();
			System.out.println(date);
			System.out.println(d1.toInstant().toEpochMilli());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/*String string = "2016-01-07";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date;
		try {
			date = format.parse(string);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}