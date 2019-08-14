package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.AnimalSupport;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Ray extends AbstractAnimal
{
	@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getCartilaginousFishCharacteristics());
		characteristics.addAll(Arrays.asList("They are flattened", "They have five gill openings"));
		return characteristics;
	}
}
