package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.Reptile;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Crocodile extends AbstractAnimal implements Reptile
{

	@Override
	public List<String> getParentCharacteristics() {
		return Reptile.super.getCharacteristics();
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
        characteristics.addAll(Arrays.asList("They have V-shaped snouts", "Toothy grin"));
	}
}
