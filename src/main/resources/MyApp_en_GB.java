package com.jeannot.zzztest.resourcebundles;

import java.util.ListResourceBundle;

/**
 * Java version of the resource bundle. This should be picked up before a properties file.
 * Useful if you want to store properties which aren't Strings...
 * But what's a good package to put them in? Surely it should be mixed up with the other classes somewhere...
 * @author milnerpl
 *
 */
public class MyApp_en_GB extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
			{"this_property_from_a_Java_class", "I got this property from a class!"}
		};
	}

}
