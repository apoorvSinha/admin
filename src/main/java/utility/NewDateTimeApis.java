package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewDateTimeApis {
	
	public static String TimeStamp() {
		LocalDateTime ldt = LocalDateTime.now();
		String format = "YYMMDD hhmmss";
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		String TimeStamp = df.format(ldt);
		return TimeStamp;
	}

	
}
