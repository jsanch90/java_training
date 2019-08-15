package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.Invertebrate;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Octopus extends AbstractAnimal implements Invertebrate
{
	@Override
	public List<String> getParentCharacteristics() {
		return Invertebrate.super.getCharacteristics();
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
        characteristics.add("They have eight legs");
	}
}
