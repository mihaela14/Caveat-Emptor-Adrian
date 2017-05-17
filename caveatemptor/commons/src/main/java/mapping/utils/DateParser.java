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

	public static Timestamp getTimestamp(String date, String time,
			String pattern) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		DateTime dateTime = formatter.parseDateTime(String.format("%s %s",
				date, time));

		return new Timestamp(dateTime.getMillis());
	}

	public static String[] getTime(Timestamp timestamp, String datePattern,
			String timePattern) {

		Date date = new Date(timestamp.getTime());

		String dates[] = new String[2];

		String formattedDate = new SimpleDateFormat(datePattern).format(date);
		String formattedTime = new SimpleDateFormat(timePattern).format(date);

		dates[0] = formattedDate;
		dates[1] = formattedTime;

		return dates;
	}
	
}
