package com.launch.compare.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.launch.compare.util.CompareDate;
import com.launch.compare.util.DateException;

public class CompareDateTest {
	
	@Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCompare() throws DateException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		CompareDate compareDate = new CompareDate();
		 Assert.assertTrue(compareDate.compare(sdf.parse("11 11 1999"), sdf.parse("11 12 1999")));
		 thrown.expect(DateException.class);
		 Assert.assertEquals("Date 1 should be earlier to Date 2", compareDate.compare(sdf.parse("11 12 1999"), sdf.parse("11 01 1999")));
	}

	@Test
	public void testValidateDateRange() throws DateException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		CompareDate compareDate = new CompareDate();
		 Assert.assertTrue(compareDate.validateDateRange(sdf.parse("11 11 1999")));
		 
		 thrown.expect(DateException.class);
		 Assert.assertEquals("Please enter a date between 1900 and 2010", compareDate.validateDateRange(sdf.parse("11 12 1899")));
		 thrown.expect(DateException.class);
		 Assert.assertEquals("Please enter a date between 1900 and 2010", compareDate.validateDateRange(sdf.parse("11 12 2016")));
	}

	@Test
	public void testIsValidFormat() throws DateException, ParseException {
		CompareDate compareDate = new CompareDate();
		String format = "dd MM yyyy";
		 Assert.assertTrue(compareDate.isValidFormat(format, "11 11 1999"));
		 
		 thrown.expect(DateException.class);
		 Assert.assertEquals("Invalid Date Format", compareDate.isValidFormat(format, "11/11/1999"));
		 thrown.expect(DateException.class);
		 Assert.assertEquals("Invalid Date Format", compareDate.isValidFormat(format, "11111999"));
	}
	
	@Test
	public void testGetDateDiff() throws DateException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		CompareDate compareDate = new CompareDate();
		Assert.assertEquals(10, compareDate.getDateDiff(sdf.parse("01 01 1999"), sdf.parse("11 01 1999"), TimeUnit.DAYS));
		Assert.assertNotEquals(11, compareDate.getDateDiff(sdf.parse("01 01 1999"), sdf.parse("01 11 1999"), TimeUnit.DAYS));
	}

}
