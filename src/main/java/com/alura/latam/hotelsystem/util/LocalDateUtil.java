package com.alura.latam.hotelsystem.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtil {

	public static LocalDate convert(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
	
	public static LocalDate convert(String date) {
		if (date.isBlank()) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
