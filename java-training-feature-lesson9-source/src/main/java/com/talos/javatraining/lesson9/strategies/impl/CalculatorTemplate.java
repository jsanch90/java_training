package com.talos.javatraining.lesson9.strategies.impl;

import com.talos.javatraining.lesson9.strategies.CalculatorStrategy;
import com.talos.javatraining.lesson9.strategies.ProcessCalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;


public  class CalculatorTemplate implements CalculatorStrategy
{
	Function<BigDecimal,String> toStr;

	public CalculatorTemplate(Function<BigDecimal,String> toStr){
		this.toStr = toStr;
	}

	private static final MathContext CONTEXT = MathContext.DECIMAL128;

	@Override
	public String add(String a, String b)
	{
		return process(a, b, BigDecimal::add);
	}

	@Override
	public String subtract(String a, String b)
	{
		return process(a, b, BigDecimal::subtract);
	}

	@Override
	public String multiply(String a, String b)
	{
		return process(a, b, BigDecimal::multiply);
	}

	@Override
	public String divide(String a, String b)
	{
		return process(a, b, BigDecimal::divide);
	}

	private String process(String a, String b, ProcessCalculation operation)
	{
		BigDecimal newA = new BigDecimal(a, CONTEXT);
		BigDecimal newB = new BigDecimal(b, CONTEXT);
		BigDecimal result = operation.calculate(newA, newB, CONTEXT);
		return toStr.apply(result);
	}
}
