package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.Mammal;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Dog extends AbstractAnimal implements Mammal
{


	@Override
	public List<String> getParentCharacteristics() {
		return Mammal.super.getCharacteristics();
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
        characteristics.add("They bark");
	}
}
