package com.talos.javatraining.lesson3;

import com.talos.javatraining.lesson3.impl.animals.*;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class AnimalTest
{
	private static final String PACKAGE = AnimalTest.class.getPackage().getName() + ".";
	private static final List<Animal> ANIMAL_INSTANCES = Arrays
			.asList(new Worm(), new Octopus(), new Tuna(), new Salmon(), new Shark(), new Ray(), new Hagfish(), new Lamprey(),
					new Frog(), new Salamander(), new Crocodile(), new Alligator(), new Hen(), new Sparrow(), new Human(),
					new Dog());


	@Test
	public void exercise_1() throws Exception
	{
		Method method = Animal.class.getMethod("getFullDescription");

		assertTrue("Method getFullDescription should be a default method", method.isDefault());
	}

	@Test
	public void exercise_2() throws Exception
	{
		Method method = Animal.class.getMethod("getName");

		assertTrue("Method getName should be a default method", method.isDefault());
	}

	@Test
	public void exercise_3() throws Exception
	{

		Class<?> invertebrate = getClass().getClassLoader().loadClass(PACKAGE + "Invertebrate");
		Class<?> fish = getClass().getClassLoader().loadClass(PACKAGE + "Fish");
		Class<?> bonyFish = getClass().getClassLoader().loadClass(PACKAGE + "BonyFish");
		Class<?> cartilaginousFish = getClass().getClassLoader().loadClass(PACKAGE + "CartilaginousFish");
		Class<?> jawlessFish = getClass().getClassLoader().loadClass(PACKAGE + "JawlessFish");
		Class<?> amphibian = getClass().getClassLoader().loadClass(PACKAGE + "Amphibian");
		Class<?> reptile = getClass().getClassLoader().loadClass(PACKAGE + "Reptile");
		Class<?> bird = getClass().getClassLoader().loadClass(PACKAGE + "Bird");
		Class<?> mammal = getClass().getClassLoader().loadClass(PACKAGE + "Mammal");

		assertTrue(Animal.class.isAssignableFrom(invertebrate));
		assertTrue(Animal.class.isAssignableFrom(fish));
		assertTrue(Animal.class.isAssignableFrom(amphibian));
		assertTrue(Animal.class.isAssignableFrom(reptile));
		assertTrue(Animal.class.isAssignableFrom(bird));
		assertTrue(Animal.class.isAssignableFrom(mammal));
		assertTrue(Animal.class.isAssignableFrom(invertebrate));

		assertTrue(invertebrate.isAssignableFrom(Worm.class));
		assertTrue(invertebrate.isAssignableFrom(Octopus.class));
		assertTrue(bonyFish.isAssignableFrom(Tuna.class));
		assertTrue(bonyFish.isAssignableFrom(Salmon.class));
		assertTrue(cartilaginousFish.isAssignableFrom(Shark.class));
		assertTrue(cartilaginousFish.isAssignableFrom(Ray.class));
		assertTrue(jawlessFish.isAssignableFrom(Hagfish.class));
		assertTrue(jawlessFish.isAssignableFrom(Lamprey.class));
		assertTrue(amphibian.isAssignableFrom(Frog.class));
		assertTrue(amphibian.isAssignableFrom(Salamander.class));
		assertTrue(reptile.isAssignableFrom(Crocodile.class));
		assertTrue(reptile.isAssignableFrom(Alligator.class));
		assertTrue(bird.isAssignableFrom(Hen.class));
		assertTrue(bird.isAssignableFrom(Sparrow.class));
		assertTrue(mammal.isAssignableFrom(Human.class));
		assertTrue(mammal.isAssignableFrom(Dog.class));
		assertTrue(invertebrate.isAssignableFrom(Worm.class));
		assertTrue(invertebrate.isAssignableFrom(Octopus.class));

		assertTrue(fish.isAssignableFrom(bonyFish));
		assertTrue(fish.isAssignableFrom(cartilaginousFish));
		assertTrue(fish.isAssignableFrom(jawlessFish));
	}

	@Test
	public void exercise_4() throws Exception
	{
		for(Animal animal: ANIMAL_INSTANCES){
			try
			{
				animal.getClass().getDeclaredMethod("getCharacteristics");
				fail("The class " +  animal.getClass() + " has the getCharacteristics method declared. Remove it");
			}
			catch (NoSuchMethodException ex)
			{
			}
		}
	}

	@Test
	public void exercise_5() throws Exception
	{
		verifyDoesntExists(PACKAGE + "AnimalSupport", "The AnimalSupport should be removed");

		Class<?> invertebrate = getClass().getClassLoader().loadClass(PACKAGE + "Invertebrate");
		Class<?> fish = getClass().getClassLoader().loadClass(PACKAGE + "Fish");
		Class<?> bonyFish = getClass().getClassLoader().loadClass(PACKAGE + "BonyFish");
		Class<?> cartilaginousFish = getClass().getClassLoader().loadClass(PACKAGE + "CartilaginousFish");
		Class<?> jawlessFish = getClass().getClassLoader().loadClass(PACKAGE + "JawlessFish");
		Class<?> amphibian = getClass().getClassLoader().loadClass(PACKAGE + "Amphibian");
		Class<?> reptile = getClass().getClassLoader().loadClass(PACKAGE + "Reptile");
		Class<?> bird = getClass().getClassLoader().loadClass(PACKAGE + "Bird");
		Class<?> mammal = getClass().getClassLoader().loadClass(PACKAGE + "Mammal");
		List<Class<?>> classes = Arrays.asList(invertebrate, fish, bonyFish, cartilaginousFish, jawlessFish, amphibian, reptile, bird, mammal);
		for (Class<?> clazz : classes)
		{
			try
			{
				Method method = clazz.getMethod("getCharacteristics");
				assertTrue("The default method getCharacteristics on " + clazz.getName() + " is missing", method.isDefault());
			}
			catch (NoSuchMethodException ex)
			{
				assertTrue(clazz.getName() + " : " + ex.getMessage(), false);
			}
		}
		Map<String, String[]> expectations = createExpectations();
		ANIMAL_INSTANCES.forEach(a -> checkCharacteristics(a, expectations.get(a.getName())));
	}

	@Test
	public void exercise_6() throws Exception
	{
		Method method = Animal.class.getMethod("create", String.class);
		checkMethod(method, "Worm");
		checkMethod(method, "Octopus");
		checkMethod(method, "Tuna");
		checkMethod(method, "Salmon");
		checkMethod(method, "Shark");
		checkMethod(method, "Ray");
		checkMethod(method, "Hagfish");
		checkMethod(method, "Lamprey");
		checkMethod(method, "Frog");
		checkMethod(method, "Salamander");
		checkMethod(method, "Crocodile");
		checkMethod(method, "Alligator");
		checkMethod(method, "Hen");
		checkMethod(method, "Sparrow");
		checkMethod(method, "Human");
		checkMethod(method, "Dog");

		assertNull("Expected null", method.invoke(null, "Other"));
		assertNull("Expected null", method.invoke(null, "Dummy"));
	}

	private void checkMethod(Method method, String name) throws Exception
	{
		Object obj = method.invoke(null, name);
		assertNotNull("Returned null with name: " +  name, obj);
		assertTrue(obj instanceof Animal);
		Animal animal = (Animal) obj;
		System.out.println(animal.getFullDescription());
	}

	private void checkCharacteristics(Animal animal, String[] expected)
	{
		assertNotNull(animal.getName() + " doesn't have the expected values", expected);
		List<String> characteristics = animal.getCharacteristics();
		Arrays.sort(expected);
		characteristics.sort(String::compareTo);
		String[] actual = characteristics.toArray(new String[characteristics.size()]);

		assertArrayEquals("The expected characteristics are different for " + animal.getName(), expected, actual);
	}

	private Map<String, String[]> createExpectations()
	{
		Map<String, String[]> expectations = new HashMap<>();
		expectations.put("Worm",
				new String[] { "Lack of backbones and internal skeletons", "Simple anatomy", "They are bilaterally symmetrical" });
		expectations.put("Octopus",
				new String[] { "Lack of backbones and internal skeletons", "Simple anatomy", "They have eight legs" });
		expectations.put("Tuna",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They have skeletons primarily composed of bone tissue", "Excellent swimmers" });
		expectations.put("Salmon",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They have skeletons primarily composed of bone tissue", "They are anadromous fish" });
		expectations.put("Shark",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They have skeleton made of cartilage rather than bone", "Highly developed senses" });
		expectations.put("Ray",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They have skeleton made of cartilage rather than bone", "They are flattened",
						"They have five gill openings" });
		expectations.put("Hagfish",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They don't have jaw", "They are living fossils" });
		expectations.put("Lamprey",
				new String[] { "They breathe using gills", "They have dominated the world's oceans, lakes and rivers",
						"They don't have jaw", "They have a sucker mouth with horny teeth and a rasping tongue" });
		expectations.put("Frog", new String[] { "They have a semi-aquatic lifestyle",
				"They have to stay near bodies of water, both to maintain the moisture of their skin and to lay their eggs",
				"They croak" });
		expectations.put("Salamander", new String[] { "They have a semi-aquatic lifestyle",
				"They have to stay near bodies of water, both to maintain the moisture of their skin and to lay their eggs",
				"They have tail that stays attached" });
		expectations.put("Crocodile", new String[] { "They ruled the earth for over 150 million years",
				"They can lay some distance away from bodies of water", "They have V-shaped snouts", "Toothy grin" });
		expectations.put("Alligator", new String[] { "They ruled the earth for over 150 million years",
				"They can lay some distance away from bodies of water", "They have U-shaped snouts" });
		expectations.put("Hen", new String[] { "Coat of feathers", "Warm-blooded metabolisms", "They don't fly" });
		expectations.put("Sparrow", new String[] { "Coat of feathers", "Warm-blooded metabolisms", "They fly", "They sing" });
		expectations.put("Human",
				new String[] { "They have hair or fur", "They suckle milk when they are young", "They rule the word!" });
		expectations.put("Dog", new String[] { "They have hair or fur", "They suckle milk when they are young", "They bark" });
		return expectations;
	}

	void verifyDoesntExists(String clazz, String failureMessage){
		try
		{
			getClass().getClassLoader().loadClass(clazz);
			fail(failureMessage);
		}
		catch (NoClassDefFoundError | ClassNotFoundException err)
		{
			// nothing to do. Expected scenario
		}
	}
}
