package com.talos.javatraining.lesson3.impl;

import com.talos.javatraining.lesson3.Animal;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;



public abstract class AbstractAnimal implements Animal
{

	@Override
	public List<String> getCharacteristics()
	{
		List<String> characteristics = new ArrayList<>(getParentCharacteristics());
		populateCharacteristics(characteristics);
		return characteristics;
	}


	public abstract List<String> getParentCharacteristics();

	public abstract void populateCharacteristics(List<String> characteristics);

}
