package com.ocbcmcd.sapfilewatcher.encrypt;

import java.util.Calendar;

public class DateString {
	private Calendar calendar = Calendar.getInstance();
	
	@Override
	public String toString() {
		return String.format("%s%s%s", calendar.get(Calendar.YEAR), getMonth(), getDay());
	}

	private String getDay() {
		return fitToTwoDigit(calendar.get(Calendar.DAY_OF_MONTH));
	}

	private String getMonth() {
		return fitToTwoDigit(calendar.get(Calendar.MONTH) + 1);
	}

	private String fitToTwoDigit(int i) {
		if (i < 10) {
			return "0" + i;
		}
		return "" + i;
	}
	
}
