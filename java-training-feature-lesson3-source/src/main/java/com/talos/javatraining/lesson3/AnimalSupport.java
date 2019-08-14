package com.talos.javatraining.lesson3;


import java.util.List;

import static java.util.Arrays.asList;


/**
 * In the excercise 5 this class should be removed
 */
@Deprecated
public class AnimalSupport
{
	public static List<String> getInvertebrateCharacteristics()
	{
		return asList("Lack of backbones and internal skeletons", "Simple anatomy");
	}

	public static List<String> getFishCharacteristics()
	{
		return asList("They breathe using gills", "They have dominated the world's oceans, lakes and rivers");
	}

	public static List<String> getAmphibianCharacteristics()
	{
		return asList("They have a semi-aquatic lifestyle",
				"They have to stay near bodies of water, both to maintain the moisture of their skin and to lay their eggs");
	}

	public static List<String> getReptileCharacteristics()
	{
		return asList("They ruled the earth for over 150 million years", "They can lay some distance away from bodies of water");
	}

	public static List<String> getBirdCharacteristics()
	{
		return asList("Coat of feathers", "Warm-blooded metabolisms");
	}

	public static List<String> getMammalCharacteristics()
	{
		return asList("They have hair or fur", "They suckle milk when they are young");
	}

	public static List<String> getBonyFishCharacteristics()
	{
		return asList("They have skeletons primarily composed of bone tissue");
	}

	public static List<String> getCartilaginousFishCharacteristics()
	{
		return asList("They have skeleton made of cartilage rather than bone");
	}

	public static List<String> getJawlessFishCharacteristics()
	{
		return asList("They don't have jaw");
	}

}
