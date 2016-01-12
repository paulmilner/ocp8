package com.jeannot.zzztest.chapter5;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

	public static void main(String[] args) {
		
		System.out.println("Starting...");

		Arrays.asList(Locale.getAvailableLocales()).stream().forEach(System.out::println);
				
		//I default to UK
		System.out.println(Locale.getDefault());
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
		
		//German formats
		Locale.setDefault(Locale.GERMANY);
		System.out.println(Locale.getDefault());
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

		//US formats
		Locale.setDefault(Locale.US);
		System.out.println(Locale.getDefault());
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

		//Resource bundles and Properties
		Locale.setDefault(Locale.UK); //Back to UK
		ResourceBundle rbUk = ResourceBundle.getBundle("MyApp");
		System.out.println(rbUk.getString("hello"));
		System.out.println(rbUk.getString("blurb"));
		System.out.println(rbUk.getString("example1"));
		

		Locale.setDefault(Locale.GERMANY);
		ResourceBundle rbGermany = ResourceBundle.getBundle("MyApp");
		System.out.println(rbGermany.getString("hello"));
		System.out.println(rbGermany.getString("blurb"));

		Locale.setDefault(Locale.US);
		ResourceBundle rbUs = ResourceBundle.getBundle("MyApp");
		System.out.println(rbUs.getString("hello"));
		System.out.println(rbUs.getString("blurb"));

		Locale.setDefault(new Locale.Builder().setLanguage("de").setRegion("CH").build()); //de_CH, I hope!
		ResourceBundle rbSwitzerland = ResourceBundle.getBundle("MyApp");
		System.out.println(rbSwitzerland.getString("hello"));
		System.out.println(rbSwitzerland.getString("blurb"));
		
		//Order in which Java tries to look up properties...
		//Requested full locale, Java class, then properties file
		//Requested language (no region), Java class, then properties file
		//Default locale, Java class, then properties file
		//Default language (no region), Java class, then properties file
		//No locale (default bundle, e.g. MyApp.properties), Java class, then properties file
		System.out.println(rbUs.getString("basic_word_2"));
		System.out.println(rbUk.getString("basic_word"));
		
		//Property from a class!
		Locale.setDefault(Locale.UK); //Back to UK
		ResourceBundle rbUkFromJavaClass = ResourceBundle.getBundle("com.jeannot.zzztest.resourcebundles.MyApp");
		System.out.println(rbUkFromJavaClass.getString("this_property_from_a_Java_class"));
		System.out.println(rbUk.getString("this_property_is_in_the_properties_file"));
		
		//Variables inside properties
		String usefulMessage = rbUk.getString("usefulMessage");
		String output = MessageFormat.format(usefulMessage, rbUk.keySet().size(), LocalDateTime.now());
		System.out.println(output);
		
		System.out.println("Done.");
	}

}
