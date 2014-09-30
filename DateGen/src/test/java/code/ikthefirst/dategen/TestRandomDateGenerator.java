package code.ikthefirst.dategen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRandomDateGenerator {

	@Test(expected = RuntimeException.class)
	public void dateGeneratorShouldThrowExceptionOnWrongDateFormat() {
		RandomDateGenerator dateGenerator = new RandomDateGenerator();
		dateGenerator.setFormat(new SimpleDateFormat("yyyy.MM.dd"));
		String startDate = "98.1.0";
		String endDate = "99yy.12.31";
		dateGenerator.generateDate(startDate, endDate);
	}

	@Test(expected = RuntimeException.class)
	public void dateGeneratorShouldThrowExceptionIfStartDateIsAfterEndDate() {
		RandomDateGenerator dateGenerator = new RandomDateGenerator();
		dateGenerator.setFormat(new SimpleDateFormat("yyyy.MM.dd"));
		String startDate = "1999.01.01";
		String endDate = "1998.12.31";
		dateGenerator.generateDate(startDate, endDate);
	}

	@Test
	public void dateGeneratorShouldReturnDateAfterStartDate() {
		RandomDateGenerator dateGenerator = new RandomDateGenerator();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		dateGenerator.setFormat(format);
		String startDate = "1999.12.30";
		String endDate = "1999.12.31";
		Date start = null;
		try {
			start = format.parse(startDate);
		} catch (ParseException e) {
			fail("Unexpected parse exception: " + e.getMessage());
		}
		Date date = dateGenerator.generateDate(startDate, endDate);
		System.out.println("Generated date : " + date);
		assertTrue("Generated date should be after start date.",
				start.getTime() <= date.getTime());
	}

	@Test
	public void dateGeneratorShouldReturnDateBeforeEndDate() {
		RandomDateGenerator dateGenerator = new RandomDateGenerator();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		dateGenerator.setFormat(format);
		String startDate = "1999.12.30";
		String endDate = "1999.12.31";
		Date end = null;
		try {
			end = format.parse(endDate);
		} catch (ParseException e) {
			fail("Unexpected parse exception: " + e.getMessage());
		}
		Date date = dateGenerator.generateDate(startDate, endDate);
		System.out.println("Generated date : " + date);
		assertTrue("Generated date should be after start date.",
				date.getTime() <= end.getTime());
	}

}
