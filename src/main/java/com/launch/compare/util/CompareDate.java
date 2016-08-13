package com.launch.compare.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CompareDate {
	
	Date earliest;
	Date latest;
	SimpleDateFormat sdf;
	
	private final static String EARLY = "01 01 1900";
	private final static String LATE = "31 12 2010";
	
	public CompareDate() throws DateException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		try {
			earliest = sdf.parse(EARLY);
			latest = sdf.parse(LATE);
		} catch (ParseException e) {
			throw new DateException("Invalid Date format");
		}		
	}
	
	public boolean compare(Date date1, Date date2) throws DateException{
		if(date1.compareTo(date2) > 0){
			throw new DateException("Date 1 should be earlier to Date 2");
		}
		return true;
	}
	
	public boolean validateDateRange(Date date1) throws DateException{
		if(date1.compareTo(earliest) < 0 || date1.compareTo(latest) > 0){
			throw new DateException("Please enter a date between 1900 and 2010");
		}
		return true;
	}
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public boolean isValidFormat(String format, String value) throws DateException {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            throw new DateException("Invalid Date Format");
        }
        return date != null;
    }
}
