package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.AnimalSupport;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Salmon extends AbstractAnimal
{
	@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(AnimalSupport.getFishCharacteristics());
		characteristics.addAll(AnimalSupport.getBonyFishCharacteristics());
		characteristics.add("They are anadromous fish");
		return characteristics;
	}
}
