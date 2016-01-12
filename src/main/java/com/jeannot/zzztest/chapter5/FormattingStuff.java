package com.jeannot.zzztest.chapter5;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Numbers, currency and dates
 *
 */
public class FormattingStuff {

	public static void main(String[] args) {
		System.out.println("Starting...");
		
		//Formatting numbers
		NumberFormat ukNumberFormat = NumberFormat.getInstance(); //UK I guess
		System.out.println("UK:\t" + ukNumberFormat.format(234990016L));
		System.out.println("UK:\t" + ukNumberFormat.format(23.4667101D));
		NumberFormat deNumberFormat = NumberFormat.getInstance(Locale.GERMANY);
		System.out.println("DE:\t" + deNumberFormat.format(234990016L));
		System.out.println("DE:\t" + deNumberFormat.format(23.4667101D));
		NumberFormat usNumberFormat = NumberFormat.getInstance(Locale.US);
		System.out.println("US:\t" + usNumberFormat.format(234990016L));
		System.out.println("US:\t" + usNumberFormat.format(23.4667101D));
		NumberFormat caNumberFormat = NumberFormat.getInstance(Locale.CANADA);
		System.out.println("CA:\t" + caNumberFormat.format(234990016L));
		System.out.println("CA:\t" + caNumberFormat.format(23.4667101D));
		
		try {
			Number n = ukNumberFormat.parse("2,301.12"); //<--you get a Number back from parse()
			System.out.println(n.doubleValue());
			System.out.println(BigDecimal.valueOf(n.doubleValue())); //use BigDecimals for money, not doubles! (Advice)
			System.out.println(n.intValue());
			System.out.println(n.longValue());
			
			Number n2 = ukNumberFormat.parse("2709.22ch394848wf"); //this should still work, it just ignores everything after the last parseable symbol...
			System.out.println(n2.doubleValue());
			
			Number n3 = ukNumberFormat.parse("wx2709.22"); //this doesn't work. There's nothing parseable at the front...
			System.out.println(n3.doubleValue());
			
		} catch (ParseException e) {
			System.err.println("\nParsing failed: " + e.getMessage());
		}
		
		//Currencies
		NumberFormat ukCurrencyFormat = NumberFormat.getCurrencyInstance(); //UK I guess
		System.out.println("UK:\t" + ukCurrencyFormat.format(234990016L));
		System.out.println("UK:\t" + ukCurrencyFormat.format(23.4667101D));
		NumberFormat deCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		System.out.println("DE:\t" + deCurrencyFormat.format(234990016L));
		System.out.println("DE:\t" + deCurrencyFormat.format(23.4667101D));
		NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("US:\t" + usCurrencyFormat.format(234990016L));
		System.out.println("US:\t" + usCurrencyFormat.format(23.4667101D));
		
		try {
			Number n = ukCurrencyFormat.parse("£2,301.12"); //<--you get a Number back from parse()
			System.out.println(n.doubleValue());
			System.out.println(BigDecimal.valueOf(n.doubleValue())); //use BigDecimals for money, not doubles! (Advice)
			System.out.println(n.intValue());
			System.out.println(n.longValue());
			
			Number n2 = ukNumberFormat.parse("£2709.22ch394848wf"); //this should still work, it just ignores everything after the last parseable symbol...
			System.out.println(n2.doubleValue());
			
			Number n3 = ukNumberFormat.parse("£wx2709.22"); //this doesn't work. There's nothing parseable at the front...
			System.out.println(n3.doubleValue());
			
		} catch (ParseException e) {
			System.err.println("\nParsing failed: " + e.getMessage());
		}

		// int defined with underscores?? Didn't even know you could do that...
		// http://docs.oracle.com/javase/7/docs/technotes/guides/language/underscores-literals.html
		int i = 320_121_000;
		System.out.println(i);
		
		//Formatting Dates and Times
		System.out.println("-------------------- Dates & Times ---------------------------");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_DATE));
		System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(now.format(DateTimeFormatter.ISO_ORDINAL_DATE));
		
		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("MMMM, MMM, MM, M, yyyy, yy, dd, (HH) hh:mm:ss.s"); //mad...
		System.out.println(now.format(myFormatter));
		
		
		System.out.println("Done");

	}

}
