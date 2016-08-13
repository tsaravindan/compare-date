package com.launch.compare;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.launch.compare.util.CompareDate;
import com.launch.compare.util.DateException;

public class Main {

	public static void main(String[] args) {
		Console console = System.console();
		String format = "dd MM yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			CompareDate compareDate = new CompareDate();
			System.out.print("Input Date 1 (DD MM YYYY) :");
			String input1 = console.readLine();
			if(!compareDate.isValidFormat(format, input1))
				throw new DateException("Invalid Date Format");
			Date date1 = sdf.parse(input1);
			
			compareDate.validateDateRange(date1);
			
			System.out.print("Input Date 2 (DD MM YYYY) :");
			String input2 = console.readLine();
			if(!compareDate.isValidFormat(format, input2))
				throw new DateException("Invalid Date Format");
			Date date2 = sdf.parse(input2);

			compareDate.validateDateRange(date2);
			
			compareDate.compare(date1, date2);
			
			System.out.println(input1 +", "+input2+", "+compareDate.getDateDiff(date1, date2, TimeUnit.DAYS));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
