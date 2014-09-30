package code.ikthefirst.dategen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomDateGenerator implements DateGenerator {

	private SimpleDateFormat dateFormat;
	private Random random;

	public RandomDateGenerator() {
		super();
		random = new Random();
	}

	public SimpleDateFormat getFormat() {
		return dateFormat;
	}

	public void setFormat(SimpleDateFormat format) {
		this.dateFormat = format;
	}

	@Override
	public Date generateDate(String startDate, String endDate) {
		Date start = null;
		Date end = null;

		try {
			start = dateFormat.parse(startDate);
			end = dateFormat.parse(endDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		if (start.getTime() > end.getTime()) {
			throw new RuntimeException("Start date must be before end date.");
		}

		long startTime = start.getTime();
		long endTime = end.getTime();
		double r = random.nextDouble();
		long genTime = startTime + (long) (r * (endTime - startTime));

		return new Date(genTime);
	}

}
