package com.jeannot.zzztest.chapter4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Just to play with some of the functional programming stuff.
 * I should prob go thru Chapter 3 first, as generics are relevant...
 *
 */
public class FunctionalStuff {
	public static void main(String[] args) {
		System.out.println("Starting...");
		
		//Sprint is a functional interface, 1 abstract method, no implementation
		Tiger t = new Tiger(); //ch 2 - but WTF use is this?
		Animal a = new Animal("hippo",false,true);
		t.sprint(a); 
		
		//A lambda expression is a block of code that can get passed around
		//... in the sense that you can pass it as an argument
		List<String> list = Arrays.asList("t3","t2","t1");
		list.forEach((s)->{System.out.println(s);});
		
		//Sorting a list by passing in a lambda expression
		Collections.sort(list, (String p1, String p2) -> p1.compareTo(p2));
		list.forEach((s)->{System.out.println(s);});
		
		//Do something useful with a lambda, e.g. check a value depending on what is passed
		PrintMatchingAnimals.print(new Animal("horse",false,false), thisAnimal -> thisAnimal.canHop());

		List<Animal> animals = Arrays.asList(new Animal("tiger",false,false), new Animal("snake",false,true), new Animal("kangaroo",true,false),new Animal("fish",false,true));
		//animals.forEach((Animal animal)->System.out.println(animal.toString()));
		//animals.forEach(System.out::println);
		animals.forEach(thisAnimal -> thisAnimal.output());
		
		//output swimmers
		System.out.println("Swimmers:");
		animals.forEach((Animal animal2)->{PrintMatchingAnimals.print(animal2, animal3 -> animal3.canSwim());}); //need to redefine each animal, animal2, animal3???
		//Still not getting how these should be chained together...?

		System.out.println("Finished.");
	}
}

class PrintMatchingAnimals {
	static void print(Animal animal, CheckTrait checkTrait) {
		if(checkTrait.test(animal)) {
			System.out.println(animal);
		}
	}
}

@FunctionalInterface
interface CheckTrait {
	boolean test(Animal animal);
}

@FunctionalInterface
interface Sprint {
	void sprint(Animal animal);
}

class Animal {
	private String species;
	private boolean canHop;
	private boolean canSwim;
	
	Animal(String species, boolean canHop, boolean canSwim) {
		this.species = species;
		this.canHop = canHop;
		this.canSwim = canSwim;
	}
	
	void output() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Animal [species=" + species + ", canHop=" + canHop + ", canSwim=" + canSwim + "]";
	}

	String getSpecies() { return this.species; }
	boolean canHop() { return this.canHop; }
	boolean canSwim() { return this.canSwim; }
}

class Tiger implements Sprint {
	@Override
	public void sprint(Animal animal) {
		System.out.println("Animal " + animal.toString() + " is sprinting");
	}
}
