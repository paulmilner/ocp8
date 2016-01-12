package com.jeannot.zzztest.chapter5;

import java.time.*; //they're all here now, not in util
import java.time.temporal.ChronoUnit;

/**
 * The NEW Java 8 ways of doing dates/times! All static methods!
 * Chapter 5 of the OCP book
 * @author Paul
 *
 */
public class DateTime {

	public static void main(String[] args) {
		
		//Instantiate with static methods...
		System.out.println("----\"Now\" values----");
		
		LocalDate now = LocalDate.now(); //Just a date
		System.out.println(now);
		
		LocalTime localTimeNow = LocalTime.now(); //Just the TIME (no date) - to millisecond precision
		System.out.println(localTimeNow);
		
		LocalDateTime localDateTimeNow = LocalDateTime.now(); //Date and time
		System.out.println(localDateTimeNow);
		
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(); //Date time with TimeZone
		System.out.println(zonedDateTimeNow);
		
		//Instantiate specific values...
		System.out.println("\n----Specific values----");
		
		LocalDate d1 = LocalDate.of(2015, 1, 2); //Valid is 1-12 for Jan-Dec, or use Month.JANUARY
		System.out.println(d1);
		
		LocalTime t1 = LocalTime.of(10, 33, 1);
		System.out.println(t1);
		
		LocalDateTime dt1  = LocalDateTime.of(2016, 2, 3, 22, 16, 59);
		System.out.println(dt1);
		
		LocalDateTime dt2 = LocalDateTime.of(d1, t1); //from a date and a time instance together!
		System.out.println(dt2);
		
		//Time Zones
		System.out.println(ZoneId.systemDefault());
		ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
		
		//All zones, sorted by name, with offset rules
		ZoneId.getAvailableZoneIds().stream().sorted().forEach((z)->{System.out.println(z + ", rules= " + ZoneId.of(z).getRules().toString());});
		
		//Manipulations... reference value, with manipulations which return a new instance with that manipulation applied...
		LocalDateTime ldt1 = LocalDateTime.now();
		LocalDateTime manipulatedLdt = null;
		System.out.println(ldt1.toString());
		manipulatedLdt = ldt1.plusDays(10);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.plusMonths(1);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.plusYears(25);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.minusDays(15);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.minusMonths(6);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.minusYears(10);
		System.out.println(manipulatedLdt.toString());

		manipulatedLdt = ldt1.minusHours(2);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.minusMinutes(120);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.plusSeconds(1);
		System.out.println(manipulatedLdt.toString());
		manipulatedLdt = ldt1.plusNanos(1000000);
		System.out.println(manipulatedLdt.toString());
		
		//Manipulations using Period - to add/subtract a given amount of time
		Period threeWeeks = Period.ofWeeks(3);
		LocalDate d = LocalDate.of(2016,1,1);
		while (d.isBefore(LocalDate.of(2016, 12, 31))) { //add 3 weeks until end of 2016
			System.out.println("adding 3 weeks: "  + d);
			d = d.plus(threeWeeks);
		}
		while (d.isAfter(LocalDate.of(2015, 1, 1))) { //you can go back too - to start of 2015
			System.out.println("going back 3 weeks: " + d);
			d = d.minus(threeWeeks);
		}
		
		//Conventional readable/parseable form of Period...
		System.out.println(Period.of(1, 2, 3));
		System.out.println(Period.parse("P2Y3M4D").getYears());
		System.out.println(Period.parse("P104W")); //2 years, but weeks aren't stored as such. Actually shown in days i.e. P728D
		
		//Comparing dates
		LocalDate yesterday = LocalDate.now().minusDays(1);
		LocalDate today = LocalDate.now();
		System.out.println(yesterday + ", is before yesterday? " + today.isBefore(yesterday)); //must be wrong
		System.out.println(today + ", is after yesterday? " + today.isAfter(yesterday)); //must be right
		
		//Epoch
		System.out.println("Days since Jan 1 1970: " + d.toEpochDay());
		LocalDateTime t = LocalDateTime.now();
		System.out.println("Seconds since Jan 1 1970: " + t.toEpochSecond(ZoneOffset.UTC));
		sleep(1000L);
		t = LocalDateTime.now();
		System.out.println("Seconds since Jan 1 1970: " + t.toEpochSecond(ZoneOffset.UTC));
		
		//Period is for years/months/days. Duration is for smaller units: day/hour/minute/sec/ns
		Duration h = Duration.ofHours(3);
		System.out.println(h.getSeconds()); //3 hours = 10800 seconds
		
		Duration m = Duration.of(10, ChronoUnit.MINUTES);
		System.out.println(m.getSeconds()); //10 mins = 600 seconds
		
		//Differences between times
		LocalDateTime tx1 = LocalDateTime.of(2014,10,8,0,0,0);
		LocalDateTime tx2 = LocalDateTime.of(2015,10,8,0,0,0);
		System.out.println(ChronoUnit.MINUTES.between(tx1,tx2));
		
		//A moment in time
		System.out.println(Instant.now());
		System.out.println(Instant.now());
		
		//Durations are directed - i.e. can be negative
		System.out.println(Duration.between(Instant.now(),Instant.now()).getNano()); //gives zero...
		Instant i1 = Instant.now();
		sleep(3L);
		Instant i2 = Instant.now();
		System.out.println(Duration.between(i1,i2).toMillis()); //gives a value, after sleep...
		
		
	}
	
	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
