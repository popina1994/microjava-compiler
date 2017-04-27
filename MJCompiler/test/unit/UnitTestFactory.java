package unit;

public class UnitTestFactory {
	
	static final int UNIT_TEST_LEX = 0;
	static final int UNIT_TEST_SEM = 1;
	static final int UNIT_TEST_SYN = 2;
	static final int UNIT_TEST_GEN = 3;
	
	static final String TEST_UNIT_LEXER_NAME = "LEXER";
	static final String TEST_UNIT_SYNTAX_NAME = "SYNTAX";
	static final String TEST_UNIT_SEMANTIC_NAME = "SEMANTIC";
	static final String TEST_UNIT_CODE_GENERATOR_NAME = "CODE GENERATOR";
	
	UnitTest createUnitTest(int testType)
	{
		UnitTest unitTest = null;
		switch (testType)
		{
			case UNIT_TEST_LEX:
				unitTest = new UnitTestLexer(TEST_UNIT_LEXER_NAME);
				break;
			case UNIT_TEST_SYN:
				unitTest = new UnitTestSyntax(TEST_UNIT_SYNTAX_NAME);
				break;
			case UNIT_TEST_SEM:
				unitTest = new UnitTestSemantic(TEST_UNIT_SEMANTIC_NAME);
				break;
			case UNIT_TEST_GEN:
				unitTest = new UnitTestCodeGenerator(TEST_UNIT_CODE_GENERATOR_NAME);
				break;
		}
		return unitTest;
	}
	
	int numberOfTests(int testType)
	{
		switch (testType)
		{
			case UNIT_TEST_LEX:
				return UnitTestLexer.TEST_MICRO_JAVA_LEXER.length;
			case UNIT_TEST_SYN:
				return UnitTestSyntax.TEST_MICRO_JAVA_PARSER_SYNTAX.length;
			case UNIT_TEST_SEM:
				return UnitTestSemantic.TEST_MICRO_JAVA_PARSER_SEMANTIC.length;
			case UNIT_TEST_GEN:
				return UnitTestCodeGenerator.TEST_MICRO_JAVA_CODE_GENERATOR_INPUT.length;
			
		}
		return 0;
	}
	
}
