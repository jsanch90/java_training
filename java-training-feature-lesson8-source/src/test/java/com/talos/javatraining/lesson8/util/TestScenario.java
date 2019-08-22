package com.talos.javatraining.lesson8.util;

public class TestScenario
{
	private final String argument;
	private final String expected;

	public TestScenario(String argument, String expected)
	{
		this.argument = argument;
		this.expected = expected;
	}

	public String getArgument()
	{
		return argument;
	}

	public String getExpected()
	{
		return expected;
	}

	@Override
	public String toString()
	{
		return "TestScenario{" +
				"argument='" + argument + '\'' +
				", expected='" + expected + '\'' +
				'}';
	}
}
