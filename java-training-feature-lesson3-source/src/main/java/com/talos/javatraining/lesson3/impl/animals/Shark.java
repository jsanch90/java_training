package com.talos.javatraining.lesson3.impl.animals;

import com.talos.javatraining.lesson3.CartilaginousFish;
import com.talos.javatraining.lesson3.impl.AbstractAnimal;

import java.util.ArrayList;
import java.util.List;


public class Shark extends AbstractAnimal implements CartilaginousFish
{


	@Override
	public List<String> getParentCharacteristics() {
		return CartilaginousFish.super.getCharacteristics();
	}

	@Override
	public void populateCharacteristics(List<String> characteristics) {
        characteristics.add("Highly developed senses");
	}
}
