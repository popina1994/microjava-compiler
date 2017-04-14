package unit;

import org.apache.commons.cli.*;

public class Main {
	
	private static int ARG_IDX_UNIT_TEST_TYPE = 0;
	private static int ARG_IDX_UNIT_TEST_NUMBER = 1;
	private static int ARG_IDX_BUILD_INCLUDED = 2;
	private static int ARG_IDX_PREVIOUS_TESTS_INCLUDED = 3;
	
	private static final String NAME_TEST_LEXER_SHORT = "lex";
	private static final String NAME_TEST_LEXER_LONG= "lekser";
	private static final String NAME_TEST_PARSER_SYNTAX_SHORT = "sin";
	private static final String NAME_TEST_PARSER_SYNTAX_LONG = "sintaksa";
	private static final String NAME_TEST_PARSER_SEMANTIC_SHORT= "sem";
	private static final String NAME_TEST_PARSER_SEMANTIC_LONG = "semantika";
	private static final String NAME_TEST_GENERATOR_SHORT = "gen";
	private static final String NAME_TEST_GENERATOR_LONG = "generator";
	
	private static final String TEXT_TEST_STARTED = "**********************************Test zapocet******************************\n";
	private static final String TEXT_TEST_FINISHED = "**********************************Test zavrsen*******************************\n";
	
	private static final String CMD_TYPE_SHORT = "t";
	private static final String CMD_TYPE_LONG = "type";
	private static final String CMD_TYPE_DESCRIPTION = "tip testa koji se pokrece\n"
			+ "1) lex, lekser za lekser\n"
			+ "2) sin, sintaksa za sintaksu\n"
			+ "3) sem, semantika za semantiku\n"
			+ "4) gen, generator za generator\n";
	
	private static final String CMD_BUILD_SHORT = "b";
	private static final String CMD_BUILD_LONG = "build";
	private static final String CMD_BUILD_DESCRIPTION = "da li se kod ponovo build-uje";
	
	private static final String CMD_RUN_PREVIOUS_TESTS_SHORT = "rp";
	private static final String CMD_RUN_PREVIOUS_TESTS_LONG = "runPrevious";
	private static final String CMD_RUN_PREVIOUS_TESTS_DESCRIPTION = "da li su prethodni testovi ukljuceni";
	
	private static final String CMD_RUN_TEST_NUMBER_SHORT = "n";
	private static final String CMD_RUN_TEST_NUMBER_LONG = "number";
	private static final String CMD_RUN_TEST_NUMBER_DESCRIPTION = "broj unit test-a koji se bira";
	
	
	// TODO : pass test number parametar, return value and print error in case of unit test.
	//
	public static void testRun(String testType, Boolean buildIncluded, Boolean previousTestsRun, Integer testNum)
	{
		if (previousTestsRun)
		{
			switch (testType)
			{
				// Maybe another organization?
				// Let name of unit test be public and instead of NAM... use that static value.
				// 
				case NAME_TEST_PARSER_SYNTAX_SHORT:
				case NAME_TEST_PARSER_SYNTAX_LONG:
					testRun(NAME_TEST_LEXER_SHORT, buildIncluded, previousTestsRun, testNum);
					break;
				case NAME_TEST_PARSER_SEMANTIC_SHORT:
				case NAME_TEST_PARSER_SEMANTIC_LONG:
					testRun(NAME_TEST_PARSER_SYNTAX_LONG, buildIncluded, previousTestsRun, testNum);
					break;
				case NAME_TEST_GENERATOR_SHORT:
				case NAME_TEST_GENERATOR_LONG:
					testRun(NAME_TEST_PARSER_SYNTAX_LONG, buildIncluded, previousTestsRun, testNum);
					break;
					
			}
		}
		
		UnitTest unitTest = null;
		// TODO : Factory if I have time.
		//
		System.out.println(TEXT_TEST_STARTED);
		switch (testType)
		{
			case NAME_TEST_LEXER_LONG:
			case NAME_TEST_LEXER_SHORT:
				System.out.println(NAME_TEST_LEXER_LONG + "Test");
				unitTest = new UnitTestLexer();
				break;
		}
		
		if (buildIncluded)
		{
			unitTest.buildCode();
		}
		unitTest.executeTest(testNum);
		System.out.println(TEXT_TEST_FINISHED);
		
	}
	
	public static void main(String[] args) {
		Options options = new Options();
		
		Option optionType = new Option(CMD_TYPE_SHORT, CMD_TYPE_LONG, true, CMD_TYPE_DESCRIPTION);
		optionType.setRequired(true);
		// TODO : Change from number to String.
		//
		Option optionTestNumber = new Option(CMD_RUN_TEST_NUMBER_SHORT, 
											 CMD_RUN_TEST_NUMBER_LONG, 
											 true, 
											 CMD_RUN_TEST_NUMBER_DESCRIPTION);
		optionTestNumber.setRequired(false);
		Option optionBuildIncluded = new Option(CMD_BUILD_SHORT, CMD_BUILD_LONG, true, CMD_BUILD_DESCRIPTION);
		optionBuildIncluded.setRequired(false); 
		Option optionPreviousTestsIncluded = new Option(CMD_RUN_PREVIOUS_TESTS_SHORT, 
														CMD_RUN_PREVIOUS_TESTS_LONG, 
														true, 
														CMD_RUN_PREVIOUS_TESTS_DESCRIPTION);
		optionBuildIncluded.setRequired(false);
		
		options.addOption(optionType);
		options.addOption(optionTestNumber);
		options.addOption(optionPreviousTestsIncluded);
		options.addOption(optionBuildIncluded);
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;
		
		try {
			cmd = parser.parse(options, args);
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
            formatter.printHelp("Unit-test", options);
            return;
		}
		
		String type = cmd.getOptionValue(CMD_TYPE_LONG);
		System.out.println("Tip " + type);
		 
		
		Boolean buildIncluded = Boolean.parseBoolean(cmd.getOptionValue(CMD_BUILD_SHORT));
		Boolean previousTestsIncluded = Boolean.parseBoolean(cmd.getOptionValue(CMD_RUN_PREVIOUS_TESTS_SHORT));
		Integer testNum = Integer.parseInt(cmd.getOptionValue(CMD_RUN_TEST_NUMBER_LONG));
		
		System.out.println("Build ukljucen " + buildIncluded);
		System.out.println("Prethodni ukljuceni " + previousTestsIncluded);
		System.out.println("Broj testa " + testNum);
		
		
		testRun(type, buildIncluded, previousTestsIncluded, testNum);
	}

}
