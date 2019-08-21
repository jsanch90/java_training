package com.talos.javatraining.lesson7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import static java.io.File.separator;
import static java.math.MathContext.DECIMAL128;
import static java.util.stream.Collectors.toCollection;


public class Generator
{
	private static final String FILE_NAME = "data.dat";
	private static final int MAX = 20000000;
	private static final BigDecimal SEVEN = new BigDecimal(7, DECIMAL128);

	public Reader generateIfNecessary()
	{
		Reader result;
		try
		{
			String file = "." + separator + FILE_NAME;
			result = new InputStreamReader(Generator.class.getResourceAsStream(file));
		}
		catch (Exception ex)
		{
			result = generate(FILE_NAME);
		}
		return result;
	}

	public Reader generate(String filename)
	{
		System.out.println("Generating numbers.... Please hold on");
		String folder = Generator.class.getResource(".").getPath();
		File file = new File(folder, filename);
		try
		{
			System.out.println("Generating in: " + file.getCanonicalPath());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try (PrintWriter pw = new PrintWriter(file))
		{
			Set<String> values = IntStream.range(0, MAX).mapToObj(this::generateNumber).collect(toCollection(HashSet::new));
			values.forEach(pw::println);
			pw.flush();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Unable to write the file");
			e.printStackTrace();
		}
		Reader reader = null;
		try
		{
			reader = new FileReader(file);
			System.out.println("Numbers generated successfully!!");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Unable to read the file");
			e.printStackTrace();
		}
		return reader;
	}

	public String generateNumber(long i)
	{
		long k = (i * 3L) + 100L;
		BigDecimal bigDecimal = new BigDecimal(k, DECIMAL128).divide(SEVEN, 2, BigDecimal.ROUND_UP);
		return bigDecimal.toString();
	}
}
