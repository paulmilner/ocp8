package com.jeannot.zzztest.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsStuff {

	public static void main(String[] args) {
		
		List<String> animules = new ArrayList<>();
		animules.add("giraffe");
		animules.add("mouse");
		animules.add("hippo"); //Adds in order, indexed
		
		animules.get(0); //should be giraffe
		animules.add(0, "bird"); //makes bird 1st in list
		
		animules.stream().forEach((s)->System.out.println(s));
		
		List<Object> listOfAnimules = Arrays.asList(animules.toArray());
		listOfAnimules.stream().forEach((s)->System.out.println(s));
		
		try {
			listOfAnimules.add("heron"); //this list is not modifiable
		} catch (UnsupportedOperationException e) {
			System.err.println(e.getMessage());
		}
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doThing(1,"a");
		doThing(2,"b","c");
		doThing(3);
		

	}
	
	private static void doThing(int n, String...strings) {
		for (String string : strings) {
			System.out.println(string);
		}
	}

}
