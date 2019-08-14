package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.AnimalSupport;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Crocodile extends AbstractAnimal
{
	@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getReptileCharacteristics());
		characteristics.addAll(Arrays.asList("They have V-shaped snouts", "Toothy grin"));
		return characteristics;
	}
}
