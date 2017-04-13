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
	
	private static final String TEXT_TEST_STARTED = "*************Test zapocet*********\n";
	private static final String TEXT_TEST_FINISHED = "*************Test zavrsen*********\n";
	
	private static final String CMD_TYPE_SHORT = "t";
	private static final String CMD_TYPE_LONG = "type";
	private static final String CMD_TYPE_DESCRIPTION = "type of unit test which is run";
	
	private static final String CMD_BUILD_SHORT = "b";
	private static final String CMD_BUILD_LONG = "build";
	private static final String CMD_BUILD_DESCRIPTION = "should we rebuild source code";
	
	private static final String CMD_RUN_PREVIOUS_TESTS_SHORT = "rp";
	private static final String CMD_RUN_PREVIOUS_TESTS_LONG = "runPrevious";
	private static final String CMD_RUN_PREVIOUS_TESTS_DESCRIPTION = "are previous tests included";
	
	private static final String CMD_RUN_TEST_NUMBER_SHORT = "n";
	private static final String CMD_RUN_TEST_NUMBER_LONG = "number";
	private static final String CMD_RUN_TEST_NUMBER_DESCRIPTION = "number of unit test which is run";
	
	
	// TODO : pass test number parametar, return value and print error in case of unit test.
	//
	public static void testRun(String testType, Boolean buildIncluded, Boolean previousTestsRun)
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
					testRun(NAME_TEST_LEXER_SHORT, buildIncluded, previousTestsRun);
					break;
				case NAME_TEST_PARSER_SEMANTIC_SHORT:
				case NAME_TEST_PARSER_SEMANTIC_LONG:
					testRun(NAME_TEST_PARSER_SYNTAX_LONG, buildIncluded, previousTestsRun);
					break;
				case NAME_TEST_GENERATOR_SHORT:
				case NAME_TEST_GENERATOR_LONG:
					testRun(NAME_TEST_PARSER_SYNTAX_LONG, buildIncluded, previousTestsRun);
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
		//unitTest.executeTest(-1);
		System.out.println(TEXT_TEST_FINISHED);
		
	}
	
	public static void main(String[] args) {
		Options options = new Options();
		
		Option optionType = new Option(CMD_TYPE_SHORT, CMD_TYPE_LONG, true, CMD_TYPE_DESCRIPTION);
		optionType.setRequired(true);
		// TODO : Change from number to String.
		//
		Option optionTestNumber = new Option(CMD_RUN_TEST_NUMBER_SHORT, 
											 CMD_RUN_PREVIOUS_TESTS_LONG, 
											 true, 
											 CMD_RUN_PREVIOUS_TESTS_DESCRIPTION);
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
		System.out.println(type);
		
		Boolean buildIncluded = Boolean.parseBoolean(cmd.getOptionValue(CMD_BUILD_SHORT));
		Boolean previousTestsIncluded = Boolean.parseBoolean(cmd.getOptionValue(CMD_RUN_PREVIOUS_TESTS_SHORT));
		
		
		testRun(type, buildIncluded, previousTestsIncluded);
	}

}
