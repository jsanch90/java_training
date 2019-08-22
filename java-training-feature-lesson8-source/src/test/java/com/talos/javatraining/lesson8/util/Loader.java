package com.talos.javatraining.lesson8.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Loader
{
	private static final String FILE = "./data.csv";
	private static final Pattern HORIZON = Pattern.compile("# test (\\d+)([^#]+)# end test \\1");
	private static final String NL = "[\\r\\n]";
	private static final String DELIMITER = ";\\s*";

	public static Map<Integer, List<TestScenario>> loadTestScenarios() throws FileNotFoundException
	{
		InputStream stream = Loader.class.getResourceAsStream(FILE);
		Scanner scanner = new Scanner(stream);
		Map<Integer, List<TestScenario>> result = new HashMap<>();
		while (scanner.findWithinHorizon(HORIZON, 0) != null)
		{
			MatchResult matchResult = scanner.match();
			int id = Integer.valueOf(matchResult.group(1));
			String body = matchResult.group(2);
			String[] parts = body.split(NL);
			List<TestScenario> scenarios = Arrays.stream(parts).filter(StringUtils::isNotBlank).map(Loader::getScenario)
					.collect(Collectors.toList());
			result.put(id, scenarios);
		}
		return result;
	}

	private static TestScenario getScenario(String str)
	{
		String[] parts = str.split(DELIMITER);
		return new TestScenario(parts[0], parts[1]);
	}
}
