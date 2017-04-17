package unit;

import org.apache.commons.cli.*;
import utility.*;


public class Main {
	
	private static final String NAME_TEST_LEXER_SHORT = "lex";
	private static final String NAME_TEST_LEXER_LONG= "lexer";
	private static final String NAME_TEST_PARSER_SYNTAX_SHORT = "syn";
	private static final String NAME_TEST_PARSER_SYNTAX_LONG = "syntax";
	private static final String NAME_TEST_PARSER_SEMANTIC_SHORT= "sem";
	private static final String NAME_TEST_PARSER_SEMANTIC_LONG = "semantic";
	private static final String NAME_TEST_GENERATOR_SHORT = "gen";
	private static final String NAME_TEST_GENERATOR_LONG = "generator";
	
	private static final String TEXT_TEST_STARTED = "**********************************Test begin******************************";
	private static final String TEXT_TEST_FINISHED = "**********************************Test end*******************************";
	private static final String TEXT_BUILD_STARTED =  "**********************************Build begin******************************";
	private static final String TEXT_BUILD_FINISHED =  "**********************************Build end******************************";
	
	private static final String FLAG_TYPE_SHORT = "t";
	private static final String FLAG_TYPE_LONG = "type";
	private static final String FLAG_TYPE_DESCRIPTION = "tip testa koji se pokrece\n"
			+ "1) lex, lekser za lekser\n"
			+ "2) sin, sintaksa za sintaksu\n"
			+ "3) sem, semantika za semantiku\n"
			+ "4) gen, generator za generator\n";
	
	private static final String FLAG_BUILD_SHORT = "b";
	private static final String FLAG_BUILD_LONG = "build";
	private static final String FLAG_BUILD_DESCRIPTION = "da li se kod ponovo build-uje";
	
	private static final String FLAG_RUN_PREVIOUS_TESTS_SHORT = "rp";
	private static final String FLAG_RUN_PREVIOUS_TESTS_LONG = "runPrevious";
	private static final String FLAG_RUN_PREVIOUS_TESTS_DESCRIPTION = "da li su prethodni testovi ukljuceni";
	
	private static final String FLAG_RUN_TEST_NUMBER_SHORT = "n";
	private static final String FLAG_RUN_TEST_NUMBER_LONG = "number";
	private static final String FLAG_RUN_TEST_NUMBER_DESCRIPTION = "broj unit test-a koji se bira";
	
	

	
	private static void testRunWrapper(int testType, Boolean previousTestsRun,
										Integer testNum)
										throws UnitTestResult
	{
		if ( (testNum != UnitTest.RUN_ALL_TESTS) && (previousTestsRun))
		{
			throw new UnitTestResult(false, UnitTestResult.ERROR_CODE_UNIT_TEST);
		}
		
		if (testNum != UnitTest.RUN_ALL_TESTS)
		{
			testRun(testType, testNum);
		}
		else
		{
			testAllRun(testType, previousTestsRun);	
		}
	}
	
	
	private static void testAllRun(int testType, Boolean previousTestsRun) throws UnitTestResult {

		if (previousTestsRun)
		{
			switch (testType)
			{
				case UnitTestFactory.UNIT_TEST_SYN:
					testAllRun(UnitTestFactory.UNIT_TEST_LEX, previousTestsRun);
					break;
				case UnitTestFactory.UNIT_TEST_SEM:
					testAllRun(UnitTestFactory.UNIT_TEST_SYN,  previousTestsRun);
					break;
				case UnitTestFactory.UNIT_TEST_GEN:
					testAllRun(UnitTestFactory.UNIT_TEST_SEM, previousTestsRun);
					break;
			}
		}
		
		for (int testNum = 0; testNum < (new UnitTestFactory()).numberOfTests(testType); testNum ++)
		{
			testRun(testType, testNum);
		}
		
				
	}


	// TODO : pass test number parametar, return value and print error in case of unit test.
	//
	public static void testRun(int testType, 
								Integer testNum) 
								throws UnitTestResult
	
	{

		UnitTest unitTest = null;
		UnitTestResult result = null;
		unitTest = new UnitTestFactory().createUnitTest(testType);
		
		Utility.println(System.out, Utility.CYAN, TEXT_TEST_STARTED);
		result = unitTest.executeTest(testNum);
		if (!result.isSuccessful() &&
				(result.getTypeError() != UnitTestResult.ERROR_CODE_UNIT_TEST))
		{
			throw result;
		}
		else if (!result.isSuccessful())
		{ 
			
			Utility.println(System.err, Utility.RED, result.getError());
		}
		else
		{
			Utility.println(System.err, Utility.GREEN, result.getError());
		}
		Utility.println(System.out, Utility.CYAN, TEXT_TEST_FINISHED);
	}
	
	public static void buildAndPrepareWrapper(int testType, 
			Boolean previousTestsRun)throws UnitTestResult
	{
		Utility.println(System.out, Utility.CYAN, TEXT_BUILD_STARTED);
		buildAndPrepare(testType, previousTestsRun);
		Utility.println(System.out, Utility.CYAN, TEXT_BUILD_FINISHED);
	}
	
	public static void buildAndPrepare(int testType, 
										Boolean previousTestsRun)
												throws UnitTestResult
														
	{
		UnitTest unitTest = null;
		UnitTestResult result = null;
		unitTest = new UnitTestFactory().createUnitTest(testType);
		
		if (previousTestsRun)
		{
			switch (testType)
			{
				case UnitTestFactory.UNIT_TEST_SYN:
				case UnitTestFactory.UNIT_TEST_SEM:
					result = unitTest.generateSouorceCodeFromSpec();
					
					if (!result.isSuccessful())
					{
						throw result;
					}
					buildAndPrepare(UnitTestFactory.UNIT_TEST_LEX, previousTestsRun);
					break;
				case UnitTestFactory.UNIT_TEST_GEN:
					buildAndPrepare(UnitTestFactory.UNIT_TEST_SEM, previousTestsRun);
					break;
			}
		}
		

		
		
		
		if ( (testType == UnitTestFactory.UNIT_TEST_LEX)
				|| (testType == UnitTestFactory.UNIT_TEST_GEN)
				||(!previousTestsRun && (testType == UnitTestFactory.UNIT_TEST_SYN 
					|| (testType == UnitTestFactory.UNIT_TEST_SEM))))
		{
			result = unitTest.generateSouorceCodeFromSpec();
			
			if (!result.isSuccessful())
			{
				throw result;
			}
		}
		
		result = unitTest.buildSourceCodeImplementation();
		if (!result.isSuccessful())
		{
			throw result;
		}
		result = unitTest.buildUnitTestEnviroment();
		if (!result.isSuccessful())
		{
			throw result;
		}
	}
	
	public static void main(String[] args) {
		Options options = new Options();
		
		Option optionType = new Option(FLAG_TYPE_SHORT, FLAG_TYPE_LONG, true, FLAG_TYPE_DESCRIPTION);
		optionType.setRequired(true);
		Option optionTestNumber = new Option(FLAG_RUN_TEST_NUMBER_SHORT, 
											 FLAG_RUN_TEST_NUMBER_LONG, 
											 true, 
											 FLAG_RUN_TEST_NUMBER_DESCRIPTION);
		optionTestNumber.setRequired(false);
		Option optionBuildIncluded = new Option(FLAG_BUILD_SHORT, FLAG_BUILD_LONG, true, FLAG_BUILD_DESCRIPTION);
		optionBuildIncluded.setRequired(false); 
		Option optionPreviousTestsIncluded = new Option(FLAG_RUN_PREVIOUS_TESTS_SHORT, 
														FLAG_RUN_PREVIOUS_TESTS_LONG, 
														true, 
														FLAG_RUN_PREVIOUS_TESTS_DESCRIPTION);
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
		
		String type = cmd.getOptionValue(FLAG_TYPE_LONG);
		int typeCode = -1;
		
		switch (type)
		{
			case NAME_TEST_LEXER_LONG:
			case NAME_TEST_LEXER_SHORT:
				typeCode = UnitTestFactory.UNIT_TEST_LEX;
				break;
			case NAME_TEST_PARSER_SYNTAX_SHORT:
			case NAME_TEST_PARSER_SYNTAX_LONG:
				typeCode = UnitTestFactory.UNIT_TEST_SYN;
				break;
			case NAME_TEST_PARSER_SEMANTIC_SHORT:
			case NAME_TEST_PARSER_SEMANTIC_LONG:
				typeCode = UnitTestFactory.UNIT_TEST_SEM;
				break;
			case NAME_TEST_GENERATOR_SHORT:
			case NAME_TEST_GENERATOR_LONG:
				typeCode = UnitTestFactory.UNIT_TEST_GEN;
				break;
		}
		
		Boolean buildIncluded = Boolean.parseBoolean(cmd.getOptionValue(FLAG_BUILD_SHORT));
		Boolean previousTestsIncluded = Boolean.parseBoolean(cmd.getOptionValue(FLAG_RUN_PREVIOUS_TESTS_SHORT));
		Integer testNum = Integer.parseInt(cmd.getOptionValue(FLAG_RUN_TEST_NUMBER_LONG));
		
		try {
			if (buildIncluded)
			{
				buildAndPrepareWrapper(typeCode, previousTestsIncluded);
			}
			//testRunWrapper(typeCode, previousTestsIncluded, testNum);
		}
		catch (UnitTestResult result) {
			Utility.println(System.err, Utility.RED, result.getError());
		}
	}

}
