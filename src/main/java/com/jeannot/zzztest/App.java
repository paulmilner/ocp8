package com.jeannot.zzztest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

	public static void main(String[] args) {
		System.out.println("Starting...");
		
		App app = new App();
		//app.runStuff();
		//app.concurrentListStuffWithStrings();
		//app.thatDoubleColonThing();
		//app.runLocalStuff();
		app.streams();
		
		System.out.println("Finished.");
	}

	private void streams() {
		
		try {
			String contents = new String(Files.readAllBytes(Paths.get("src/main/resources/paul.txt")),StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
			long count = words.stream().filter(w -> (w.length()>12)).count();
			System.out.println(count);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void runLocalStuff() {
		
		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec("dir");
			BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while((line = buf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void thatDoubleColonThing() {
		final List<String> things = Arrays.asList("Paul","Helen","Sophie","Judy","Ronnie","Belle","Poppy","Sara");
		//things.forEach((s)->System.out.println(s));
		//OR....
		things.forEach(System.out::println);
		things.forEach(this::encase);
	}

	private void concurrentListStuffWithStrings() {
		Executor executor = Executors.newFixedThreadPool(2);
		final CountDownLatch latch = new CountDownLatch(2);
		//final List<String> things = Arrays.asList("1","2","3","4","5","6","7","8");
		//Need a synchronized list to avoid thread-safety probs
		final List<String> things = Collections.synchronizedList(Arrays.asList("1","2","3","4","5","6","7","8"));
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(0);
					System.out.println("r1 finished sleeping");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("r1 on thread " + Thread.currentThread().getName());
				things.replaceAll(s -> s + "-r1");
				latch.countDown();
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("r2 on thread " + Thread.currentThread().getName());
				things.replaceAll(s -> s + "-r2");
				latch.countDown();
			}
		};
		
		executor.execute(r1);
		executor.execute(r2);
		
		try {
			latch.await();
			System.out.println("Finished awaiting");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		things.forEach((s)->System.out.println("Final result: " + s));
	}

	private void runStuff() {
		List<String> words = new ArrayList<>();
		words.add("Thing1");
		words.add("Thing2");
		
		//Lambda function to sysout every item in the list...
		Consumer<? super String> action = (String s) -> {System.out.println("Output: " + s);};
		words.forEach(action);
		
		//Reassign lambda function
		action = (String s) -> {System.out.println("2nd Output: " + s);};
		words.forEach(action);
		
		//Or just with a lambda expression
		words.forEach((String s)->{System.out.println("3rd output: " + s);System.out.println("Did it");});
		
		//Or even more compact, where the type is inferred:
		words.forEach((s)->{System.out.println("4th output: " + s.toUpperCase());});
		
		//A lambda expression is just a box to put a code block in, which can then be reused anywhere
		//Which makes it sound like an "object", but...
		//The box looks like this:
		//()->{System.out.println("Whatever");}
		
		//Can you make changes to the underlying data?
		words.replaceAll(s -> s+"-END");
		words.forEach((s)->System.out.println("5th output: " + s));
	}
	
	private String encase(String s) {
		char[] result = new char[s.length()];
		int i = 0;
		for(char c : s.toCharArray()) {
			if (i % 2 == 0) result[i++] = Character.toLowerCase(c); else result[i++] =  Character.toUpperCase(c);
		}
		System.out.println(result);
		return new String(result);
	}
	
}
