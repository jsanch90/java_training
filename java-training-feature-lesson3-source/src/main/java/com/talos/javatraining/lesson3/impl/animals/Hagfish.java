package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.AnimalSupport;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Hagfish extends AbstractAnimal
{
	@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getJawlessFishCharacteristics());
		characteristics.add("They are living fossils");
		return characteristics;
	}
}
