package mapping.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParser {

	private DateParser() {
	}

	public static Timestamp getTimestamp(String date, String pattern) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		DateTime dateTime = formatter.parseDateTime(date);

		return new Timestamp(dateTime.getMillis());
	}

	public static String getTime(Timestamp timestamp, String pattern) {

		Date date = new Date(timestamp.getTime());
		String formattedDate = new SimpleDateFormat(pattern).format(date);

		return formattedDate;
	}
}
