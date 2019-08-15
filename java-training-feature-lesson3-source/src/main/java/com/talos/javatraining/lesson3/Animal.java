package com.talos.javatraining.lesson3;

import com.talos.javatraining.lesson3.impl.animals.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public interface Animal
{

	default String getName() {
		return getClass().getSimpleName();
	}

	List<String> getCharacteristics();

	default String getFullDescription() {
		StringBuilder builder = new StringBuilder();
		String name = getName();
		String lastChr = name.substring(name.length()-1);
		builder.append(name).append(lastChr.equals("s") ? "es" : "s").append(" have these characteristics :");
		for (String characteristic : getCharacteristics())
		{
			builder.append(StringUtils.LF).append(StringUtils.CR).append("- ").append(characteristic);
		}
		return builder.toString();
	}

	static Animal create(String name){

		final String PACKAGE = "com.talos.javatraining.lesson3.impl.animals.";
		try {
			return (Animal) Class.forName(PACKAGE + name).newInstance();

		}catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException x){
			return null;
		}

	}

}
